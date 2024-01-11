package br.com.vollmed.vollmed.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //LÓGICA PARA RECUPERAR O TOKEN
        var tokenJWT = recuperarToken(request);

        System.out.println(tokenJWT);

        //CHAMANDO O PROXIMO FILTRO
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null){
            throw new RuntimeException("Token não enviado.");
        }

        // O REPLACE ESTA DIZENDO, SUBSTITUA A PALAVRA BEARER POR NADA.
        return authorizationHeader.replace("Bearer", "");
    }
}
