package com.sam.hello_spring_boot.service;

import com.sam.hello_spring_boot.dto.request.PermissionRequest;
import com.sam.hello_spring_boot.dto.request.RoleRequest;
import com.sam.hello_spring_boot.dto.response.PermissionResponse;
import com.sam.hello_spring_boot.dto.response.RoleResponse;
import com.sam.hello_spring_boot.entity.Permission;
import com.sam.hello_spring_boot.mapper.PermissionMapper;
import com.sam.hello_spring_boot.mapper.RoleMapper;
import com.sam.hello_spring_boot.repository.PermissionRepository;
import com.sam.hello_spring_boot.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor // thay cho autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // cac bien k khai bao thi mac dinh private final
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;
    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getAll(){
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String role){
        roleRepository.deleteById(role);
    }




}
