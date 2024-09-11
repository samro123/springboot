package com.sam.hello_spring_boot.mapper;

import com.sam.hello_spring_boot.dto.request.PermissionRequest;
import com.sam.hello_spring_boot.dto.request.RoleRequest;
import com.sam.hello_spring_boot.dto.response.PermissionResponse;
import com.sam.hello_spring_boot.dto.response.RoleResponse;
import com.sam.hello_spring_boot.entity.Permission;
import com.sam.hello_spring_boot.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true) // bo qua parameter permissions k map
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);

}
