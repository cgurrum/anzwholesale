package com.anz.wholesale.controller.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SecurityTokenRequest {
    private String customerId;
    private String password;
}
