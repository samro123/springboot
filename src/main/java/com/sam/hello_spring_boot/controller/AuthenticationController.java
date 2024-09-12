package com.sam.hello_spring_boot.controller;

import com.nimbusds.jose.JOSEException;
import com.sam.hello_spring_boot.dto.request.ApiResponse;
import com.sam.hello_spring_boot.dto.request.AuthenticationRequest;
import com.sam.hello_spring_boot.dto.request.IntrospectRequest;
import com.sam.hello_spring_boot.dto.request.LogoutRequest;
import com.sam.hello_spring_boot.dto.response.AuthenticationResponse;
import com.sam.hello_spring_boot.dto.response.IntrospectResponse;
import com.sam.hello_spring_boot.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.isAuthentication(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return  ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return  ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }
}
