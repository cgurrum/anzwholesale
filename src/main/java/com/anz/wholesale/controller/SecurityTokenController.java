package com.anz.wholesale.controller;

import com.anz.wholesale.controller.models.SecurityTokenRequest;
import com.anz.wholesale.controller.models.SecurityTokenResponse;
import com.anz.wholesale.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SecurityTokenController {

    public static final String securityTokenPath = "/authenticate";
    private final SecurityService securityService;

    @PostMapping(value = securityTokenPath, produces = "application/json")
    public SecurityTokenResponse getSecurityToken(@RequestBody SecurityTokenRequest tokenRequest) {
        log.debug("SecurityTokenController: getSecurityToken for Customer: {}",tokenRequest.getCustomerId());
        String token = this.securityService.getSecurityToken(tokenRequest.getCustomerId(), tokenRequest.getPassword());
        log.info("SecurityTokenController: getSecurityToken: returning token for Customer: {}", tokenRequest.getCustomerId());
        return new SecurityTokenResponse(token);
    }
}
