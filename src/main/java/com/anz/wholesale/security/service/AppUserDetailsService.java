package com.anz.wholesale.security.service;

import com.anz.wholesale.customers.repository.CustomersRepository;
import com.anz.wholesale.customers.repository.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final CustomersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String customerId) throws UsernameNotFoundException {
        log.debug("AppUserDetailsService: loadUserByUsername for Customer: {}", customerId);
        Optional<CustomerEntity> customerEntity = repository.findById(customerId);
        return customerEntity.map(entity->{
            log.debug("AppUserDetailsService: loadUserByUsername: Found Customer: {} with Scopes: {}", entity.getId(), entity.getScopes());
            List<SimpleGrantedAuthority> scopes = Arrays.stream(entity.getScopes().split(","))
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return User.withUsername(entity.getId())
                    .password(entity.getPassword())
                    .authorities(scopes).build();
        }).orElseThrow(()->new UsernameNotFoundException("User name not found"));
    }
}
