package br.csi.PI_Backend.infra.security;

import br.csi.PI_Backend.service.autenticacao.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;
@Component
public class AutenticacaoFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = Logger.getLogger(AutenticacaoFilter.class.getName());

    private final TokenServiceJWT tokenServiceJWT;
    private final AutenticacaoService autenticacaoSerivce;

    public AutenticacaoFilter(TokenServiceJWT tokenServiceJWT, AutenticacaoService autenticacaoSerivce) {
        this.tokenServiceJWT = tokenServiceJWT;
        this.autenticacaoSerivce = autenticacaoSerivce;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        LOGGER.info("Filtro para autenticação e autorização");

        String tokenJWT = recuperarToken(request);
        LOGGER.info("tokenJWT: " + tokenJWT);

        if (tokenJWT != null) {
            try {
                String subject = this.tokenServiceJWT.getSubject(tokenJWT);
                LOGGER.info("Login da req. " + subject);

                UserDetails userDetails = this.autenticacaoSerivce.loadUserByUsername(subject);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (RuntimeException e) {
                LOGGER.severe("Authentication failed: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: " + e.getMessage());
                return;
            }
        }

        LOGGER.info("Request Path: " + request.getRequestURI());
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return token.replace("Bearer ", "").trim();
        }
        return null;
    }
}

