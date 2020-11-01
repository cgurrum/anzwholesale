package com.anz.wholesale.security.configuration;

import com.anz.wholesale.controller.SecurityTokenController;
import com.anz.wholesale.security.filter.JwtAuthenticationFilter;
import com.anz.wholesale.security.service.JwtAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final List<String> unsecuredPaths = Arrays.asList(
            SecurityTokenController.securityTokenPath,
            "/h2-console/**",
            "/api-docs.yaml"
    );

    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    public void setJwtAuthenticationProvider(JwtAuthenticationProvider jwtAuthenticationProvider){
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("SecurityConfiguration: configure: http");
        http.csrf().disable().cors()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and().addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        unsecuredPaths.forEach(web.ignoring()::antMatchers);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtAuthenticationProvider);
    }

}
