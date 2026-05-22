package com.easysoftware.sgi_api.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.easysoftware.sgi_api.config.tenant.TenantContext;
import com.easysoftware.sgi_api.repository.UsuarioRepository;
import com.easysoftware.sgi_api.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

   private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        // Ignora validações complexas se for rota pública de login
        if ("/api/login".equals(request.getRequestURI())) {
            TenantContext.setCurrentTenant("public");
            filterChain.doFilter(request, response);
            return;
        }

        var tokenJWT = recuperarToken(request);
        var tenantId = request.getHeader("X-Tenant-ID");

        try {
            // Sanitização básica e fallback seguro para o contexto do tenant
            if (tenantId != null && !tenantId.trim().isEmpty()) {
                TenantContext.setCurrentTenant(tenantId.trim().toLowerCase().replaceAll("[^a-z0-9_]", ""));
            } else {
                TenantContext.setCurrentTenant("public");
            }

            if (tokenJWT != null) {
                try {
                    var subject = tokenService.getSubject(tokenJWT);
                    var usuario = usuarioRepository.findByLogin(subject);

                    if (usuario != null) {
                        // Validação Cross-Tenant defensiva
                        if (tenantId != null && !tenantId.trim().isEmpty()) {
                            if (usuario.getFilial() == null || 
                                usuario.getFilial().getMatriz() == null || 
                                !usuario.getFilial().getMatriz().getSchemaName().equalsIgnoreCase(tenantId)) {
                                
                                emitirErroAcessoNegado(response, "Acesso negado: Contexto corporativo inválido para este usuário.");
                                return;
                            }
                        }

                        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (RuntimeException e) {
                    // Captura tokens expirados/inválidos e retorna um erro 401 limpo
                    emitirErroAutenticacao(response, e.getMessage());
                    return;
                }
            }

            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear(); // Evita contaminação de Threads do pool
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }
        return authorizationHeader.replace("Bearer ", "");
    }

    private void emitirErroAcessoNegado(HttpServletResponse response, String mensagem) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"error\": \"Forbidden\", \"message\": \"" + mensagem + "\"}");
    }

    private void emitirErroAutenticacao(HttpServletResponse response, String mensagem) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + mensagem + "\"}");
    }
}
