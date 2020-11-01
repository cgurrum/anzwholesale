package com.anz.wholesale.security.utils;

import com.anz.wholesale.exception.InvalidJwtTokenException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {
    private final String secretKey;

    @Autowired
    public JwtTokenUtil(@Value("${wholesale.security.jwt-token.secret-key:jwt-secret}") String secretKey){
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String customerId, Collection<? extends GrantedAuthority> roles) {
        log.debug("JwtTokenUtil: createToken for Customer: {}", customerId);
        Claims claims = Jwts.claims().setSubject(customerId);
        claims.put("auth", roles);
        long validityInMilliseconds = 3600000; //1hr
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        log.debug("JwtTokenUtil: createToken: returning token for Customer: {}", customerId);
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String validateTokenReturningUsername(String token) {
        try {
            log.debug("JwtTokenUtil: validateTokenReturningUsername");
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (JwtException | IllegalArgumentException ex) {
            log.error("JwtTokenUtil: validateTokenReturningUsername: Error Message: {}", ex.getMessage());
            throw new InvalidJwtTokenException("Invalid Jwt Token");
        }
    }

}
