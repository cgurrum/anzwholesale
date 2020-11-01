package com.anz.wholesale.security.service;

import com.anz.wholesale.security.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public String getSecurityToken(String customerId, String password){
        log.debug("SecurityService: getSecurityToken for Customer: {}", customerId);
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerId, password));
        log.info("SecurityService: getSecurityToken: authenticated customer");
        return jwtTokenUtil.createToken(auth.getName(), auth.getAuthorities());
    }
}

