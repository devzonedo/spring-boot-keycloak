package com.devz.auth.server.controller;

import com.devz.auth.server.model.LoginRequest;
import com.devz.auth.server.model.LoginResponse;
import com.devz.auth.server.model.Response;
import com.devz.auth.server.model.TokenRequest;
import com.devz.auth.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest){
      return  loginService.login(loginRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@RequestBody TokenRequest tokenRequest){
        return loginService.logout(tokenRequest);
    }


}
