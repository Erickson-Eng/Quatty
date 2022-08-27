package br.com.quatty.backend.application.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTValidateFilter extends BasicAuthenticationFilter {

    private static final String HEADER_ATT = "Authorization";
    private static final String PREFIX = "Bearer ";
    private final TokenService tokenService;

    public JWTValidateFilter(AuthenticationManager authenticationManager,  TokenService tokenService) {
        super(authenticationManager);
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String attribute = request.getHeader(HEADER_ATT);
        if (attribute == null || !attribute.startsWith(PREFIX)){
            chain.doFilter(request, response);
        }
        else {
            String token = attribute.replace(PREFIX, "");
            UsernamePasswordAuthenticationToken authenticationToken = tokenService.getAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request,response);
        }
    }

}
