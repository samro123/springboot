package com.sam.hello_spring_boot.mapper;

import com.sam.hello_spring_boot.dto.request.UserCreationRequest;
import com.sam.hello_spring_boot.dto.request.UserUpdateRequest;
import com.sam.hello_spring_boot.dto.response.UserResponse;
import com.sam.hello_spring_boot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    //@Mapping(source = "lastName", target = "firstName")  // 2 doi tuong UserResponse and User k co cung properties(firstName:lastName)
    //@Mapping(target = "firstName", ignore = true) // firstName:null
    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request); // Map Data tu request vao trong object user
}
