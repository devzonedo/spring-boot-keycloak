package com.devz.auth.server.service;

import com.devz.auth.server.model.LoginRequest;
import com.devz.auth.server.model.LoginResponse;
import com.devz.auth.server.model.Response;
import com.devz.auth.server.model.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class LoginService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    private String issueUrl;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.authorization-grant-type}")
    private String grantType;

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        HttpHeaders  headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("client_id",clientId);
        map.add("username",loginRequest.getUsername());
        map.add("password",loginRequest.getPassword());
        map.add("grant_type",grantType);
        map.add("client_secret",clientSecret);

        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(map,headers);
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity("http://localhost:8180/auth/realms/auth-realm/protocol/openid-connect/token", httpEntity, LoginResponse.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }


    public ResponseEntity<Response> logout(TokenRequest tokenRequest) {
        HttpHeaders  headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("client_id",clientId);
        map.add("client_secret",clientSecret);
        map.add("refresh_token",tokenRequest.getToken());

        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(map,headers);
        ResponseEntity<Response> response = restTemplate.postForEntity("http://localhost:8180/auth/realms/auth-realm/protocol/openid-connect/logout", httpEntity, Response.class);

        Response res = new Response();
        if(response.getStatusCode().is2xxSuccessful()){
            res.setMessage("Logged out successfully");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
