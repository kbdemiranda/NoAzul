package io.github.noazul.config.security;

import io.github.noazul.domain.Usuario;
import io.github.noazul.repository.UsuarioRepository;
import io.github.noazul.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoFiilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public AutenticacaoFiilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token == null || token.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.replace("Bearer ", "");
        boolean tokenValido = tokenService.isValido(token);
        if (tokenValido) {
            Long idUsuario = tokenService.extrairIdUsuario(token);
            Usuario usuario = usuarioRepository.carregarPorIdComPerfis(idUsuario).orElseThrow();
            Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
