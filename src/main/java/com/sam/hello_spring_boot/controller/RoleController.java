package com.sam.hello_spring_boot.controller;

import com.sam.hello_spring_boot.dto.request.ApiResponse;
import com.sam.hello_spring_boot.dto.request.PermissionRequest;
import com.sam.hello_spring_boot.dto.request.RoleRequest;
import com.sam.hello_spring_boot.dto.response.PermissionResponse;
import com.sam.hello_spring_boot.dto.response.RoleResponse;
import com.sam.hello_spring_boot.service.PermissionService;
import com.sam.hello_spring_boot.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .code(1000)
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .code(1000)
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }


}
