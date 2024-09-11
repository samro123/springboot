package com.sam.hello_spring_boot.service;

import com.sam.hello_spring_boot.dto.request.UserCreationRequest;
import com.sam.hello_spring_boot.dto.request.UserUpdateRequest;
import com.sam.hello_spring_boot.dto.response.UserResponse;
import com.sam.hello_spring_boot.entity.User;
import com.sam.hello_spring_boot.enums.Role;
import com.sam.hello_spring_boot.exception.AppException;
import com.sam.hello_spring_boot.exception.ErrorCode;
import com.sam.hello_spring_boot.mapper.UserMapper;
import com.sam.hello_spring_boot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor // thay cho autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // cac bien k khai bao thi mac dinh private final
@Slf4j
public class UserService {
     UserRepository userRepository; //cac Service chi dc goi xuong Repository

     UserMapper userMapper; //

     PasswordEncoder passwordEncoder;

    public User createUser(UserCreationRequest request){


        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_ENUM); // Kiem tra user co ton tai hay k
        User user = userMapper.toUser(request); //map request vao user

        //ma hoa password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        //user.setRoles(roles);

        return userRepository.save(user);
    }

    public  UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not Found"));
        userMapper.updateUser(user, request);
        return  userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);

    }


    @PreAuthorize("hasRole('ADMIN')") // Kiem tra truoc luc goi ham co Role Admin
    public List<UserResponse> getUsers(){
        log.info("In method get Users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    //lay ra 1 user
    //returnObject.username la UserResponse username
    // id cua username co trung vs username authentication , user chi lay thong tin chinh minh ma thoi
    @PostAuthorize("returnObject.username == authentication.name") //
    public UserResponse getUser(String id){
        return  userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not Found")));
    }


 }
