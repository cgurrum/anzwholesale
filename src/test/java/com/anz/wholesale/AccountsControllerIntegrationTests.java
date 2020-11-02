package com.anz.wholesale;

import com.anz.wholesale.accounts.models.Account;
import com.anz.wholesale.exception.models.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.MultiValueMap;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountsControllerIntegrationTests {
    @LocalServerPort private int port;
    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void testAccountsNoToken() {
        ResponseEntity<ErrorMessage> response = this.restTemplate
                .getForEntity(getAccountsUrl(), ErrorMessage.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testAccountsInvalidToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJjdXN0b21lcjEiLCJhdXRoIjpbeyJhdXRob3JpdHkiOiJTQ1BfQUNDT1VOVFMifSx7ImF1" +
                        "dGhvcml0eSI6IlNDUF9EVU1NWSJ9XSwiaWF0IjoxNjA0MjA4NjM1LCJleHAiOjE2MDQyMTIyMzV9" +
                        ".UljkYAM2GHJ1GinQmAjGASVb8j785AmYKioY8s42hn8");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(
                getAccountsUrl(), HttpMethod.GET, entity, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testAccountsDeniedScope() {
        String longLiveInvalidScopeToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lc" +
                "jMiLCJhdXRoIjpbeyJhdXRob3JpdHkiOiJTQ1BfRFVNTVkifV0sImlhdCI6MTYwND" +
                "I5MzQ3MCwiZXhwIjoxNjA2MTIwODU3fQ.X4wzZ8QNpD8HMIZWMqJ_9LHT-S8HIG7XvYhWChIQ0cA";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+longLiveInvalidScopeToken);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> accountResponse = restTemplate.exchange(
                getAccountsUrl(), HttpMethod.GET, entity, Object.class);
        assertThat(accountResponse.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void testAccountsSuccess() {
        String longLiveToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjEiLCJhdXRoIjpbeyJhd" +
                "XRob3JpdHkiOiJTQ1BfQUNDT1VOVFMifSx7ImF1dGhvcml0eSI6IlNDUF9EVU1NWSJ9XSwiaWF0Ij" +
                "oxNjA0MjkzNDM4LCJleHAiOjE2MDYxMjA4MjV9.yhZfk-ktctmpYgikorJBoicVe8MiidmLL_p-r50MwXY";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+longLiveToken);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        ResponseEntity<Account[]> accountResponse = restTemplate.exchange(
                getAccountsUrl(), HttpMethod.GET, entity, Account[].class);
        assertThat(accountResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(accountResponse.getBody()).isNotNull();
        assertThat(accountResponse.getBody().length).isGreaterThan(0);
    }



    private String getAccountsUrl(){
        return "http://localhost:"+this.port+"/accounts";
    }

}
