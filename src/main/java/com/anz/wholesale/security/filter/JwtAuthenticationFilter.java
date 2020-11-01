package com.anz.wholesale.security.filter;

import com.anz.wholesale.exception.JwtTokenMissingException;
import com.anz.wholesale.security.service.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("JwtAuthenticationFilter: doFilterInternal");
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            Authentication auth = jwtAuthenticationProvider.getAuthenticationByToken(bearerToken.substring(7));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }else{
            throw new JwtTokenMissingException("Jwt Token missing in the request header");
        }
        filterChain.doFilter(request, response);
    }

}
