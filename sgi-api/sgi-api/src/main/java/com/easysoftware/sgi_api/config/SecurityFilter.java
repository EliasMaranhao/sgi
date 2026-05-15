package com.easysoftware.sgi_api.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.easysoftware.sgi_api.repository.UsuarioRepository;
import com.easysoftware.sgi_api.service.TokenService;
import com.easysoftware.sgi_api.tenant.TenantContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository){
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        var tenantId = request.getHeader("X-Tenant-ID");

        try{
            if (tenantId != null && !tenantId.isEmpty()) {
                TenantContext.setTenantId(tenantId);
            }

            if (tokenJWT != null) {
                var subject = tokenService.getSubject(tokenJWT);
                var usuario = usuarioRepository.findByLogin(subject);

                if (tenantId != null && !usuario.getFilial().getMatriz().getTenantId().equals(tenantId)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("Acesso negado: Você não tem permissão para acessar esta unidade.");
                    return; // Interrompe o fluxo da requisição
                }

                // Cria o objeto que diz ao Spring que o usuário está autenticado
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }finally{
            TenantContext.clear();
        }
        
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }

        return authorizationHeader.replace("Bearer ", "");
    }
}
