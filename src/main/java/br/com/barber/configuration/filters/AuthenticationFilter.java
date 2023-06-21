package br.com.barber.configuration.filters;

import br.com.barber.service.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    public AuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.getBearerToken(request);

        if(Objects.nonNull(token)){
            tokenService.isValid(token);
        }

        filterChain.doFilter(request, response);
    }
    private String getBearerToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(Objects.isNull(token) || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7);
    }
}
