package com.sam.hello_spring_boot.service;

import com.sam.hello_spring_boot.dto.request.PermissionRequest;
import com.sam.hello_spring_boot.dto.response.PermissionResponse;
import com.sam.hello_spring_boot.entity.Permission;
import com.sam.hello_spring_boot.mapper.PermissionMapper;
import com.sam.hello_spring_boot.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // thay cho autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // cac bien k khai bao thi mac dinh private final
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}
