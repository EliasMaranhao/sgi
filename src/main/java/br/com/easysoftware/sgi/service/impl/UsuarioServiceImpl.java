package br.com.easysoftware.sgi.service.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import br.com.easysoftware.sgi.dto.LoginRequestDTO;
import br.com.easysoftware.sgi.dto.LoginResponseDTO;
import br.com.easysoftware.sgi.entity.Role;
import br.com.easysoftware.sgi.entity.Usuario;
import br.com.easysoftware.sgi.exception.RecursoJaCadastradoException;
import br.com.easysoftware.sgi.exception.RecursoNaoExisteException;
import br.com.easysoftware.sgi.repository.RoleRepository;
import br.com.easysoftware.sgi.repository.UsuarioRepository;
import br.com.easysoftware.sgi.service.UsuarioService;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder jwtEncoder;
    private final RoleRepository roleRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
       var usuario = buscarPeloNome(loginRequestDTO.username());

        if(!usuario.isLoginCorrect(loginRequestDTO, bCryptPasswordEncoder)){
            throw new BadCredentialsException("Usuário ou senha inválido");
        }

        var expiresIn = 300L;
        var now = Instant.now();

        var roles = usuario.getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                                    .issuer("SGI_API")
                                    .subject(String.valueOf(usuario.getId()))
                                    .issuedAt(now)
                                    .expiresAt(now.plusSeconds(expiresIn))
                                    .claim("scope", roles)
                                    .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        
        return new LoginResponseDTO(jwtValue, expiresIn);
    }

    @Override
    public void salvar(Usuario usuario) {
        Optional<Usuario> optional = usuarioRepository.findByUsername(usuario.getUsername());

        optional.ifPresentOrElse(
            (usuar) -> {
                throw new RecursoJaCadastradoException("Este usuario ja está cadastrado na base");
            },
            () -> {
                Set<Role> roles = new HashSet<>();
                Iterator<Role> roleAsIterator = usuario.getRoles().iterator();
                while(roleAsIterator.hasNext()){
                    roles.add(roleRepository.findRoleByName(roleAsIterator.next().getName()));
                }

                String senha = usuario.getPassword();
                usuario.setPassword(bCryptPasswordEncoder.encode(senha));
                usuario.setRoles(roles);
                usuarioRepository.save(usuario);
            }
        );
        

    }
    
    private Usuario buscarPeloNome(String nome){
        Optional<Usuario> optional = usuarioRepository.findByUsername(nome);
        if(optional.isEmpty()){
            throw new RecursoNaoExisteException("Rescurso não localizado");
        }

        return optional.get();
    }

    @Override
    public List<Usuario> listarTodos() {
       return usuarioRepository.findAll();
    }
}
