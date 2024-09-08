package com.sam.hello_spring_boot.controller;

import com.sam.hello_spring_boot.dto.request.ApiResponse;
import com.sam.hello_spring_boot.dto.request.AuthenticationRequest;
import com.sam.hello_spring_boot.dto.response.AuthenticationResponse;
import com.sam.hello_spring_boot.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        boolean result = authenticationService.isAuthentication(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse
                        .builder()
                        .idAuthentication(result)
                        .build())
                .build();
    }
}
