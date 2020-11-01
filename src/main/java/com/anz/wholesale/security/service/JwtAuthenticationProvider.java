package com.anz.wholesale.security.service;

import com.anz.wholesale.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationProvider {
    private AppUserDetailsService appUserDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public void setAppUserDetailsService(AppUserDetailsService appUserDetailsService){
        this.appUserDetailsService = appUserDetailsService;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil){
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public Authentication getAuthenticationByToken(String token) {
        String username = jwtTokenUtil.validateTokenReturningUsername(token);
        UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
