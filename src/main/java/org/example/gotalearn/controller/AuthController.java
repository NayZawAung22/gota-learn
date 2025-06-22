package org.example.gotalearn.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.example.gotalearn.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    record LoginRequest(String username,String password){}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        String responseString = authService.login(loginRequest.username,
                loginRequest.password);
        return ResponseEntity.ok(responseString);
    }
    record RegisterDto(String username,
                       String password,
                       String email,
                       String address,
                       String education,
                       String experties,
                       @JsonProperty("user_type") String userType){}
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto){
        String responseString=authService.register(registerDto.username,
                registerDto.password,
                registerDto.email,
                registerDto.address,
                registerDto.education,
                registerDto.experties,
                registerDto.userType);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseString);
    }


}
