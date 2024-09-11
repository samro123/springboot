package com.sam.hello_spring_boot.controller;

import com.nimbusds.jose.JOSEException;
import com.sam.hello_spring_boot.dto.request.ApiResponse;
import com.sam.hello_spring_boot.dto.request.AuthenticationRequest;
import com.sam.hello_spring_boot.dto.request.IntrospectRequest;
import com.sam.hello_spring_boot.dto.request.PermissionRequest;
import com.sam.hello_spring_boot.dto.response.AuthenticationResponse;
import com.sam.hello_spring_boot.dto.response.IntrospectResponse;
import com.sam.hello_spring_boot.dto.response.PermissionResponse;
import com.sam.hello_spring_boot.service.AuthenticationService;
import com.sam.hello_spring_boot.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/permissions")
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .code(1000)
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(1000)
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission){
        permissionService.delete(permission);
        return ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }


}
