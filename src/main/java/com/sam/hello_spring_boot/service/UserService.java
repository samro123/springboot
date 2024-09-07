package com.sam.hello_spring_boot.service;

import com.sam.hello_spring_boot.dto.request.UserCreationRequest;
import com.sam.hello_spring_boot.dto.request.UserUpdateRequest;
import com.sam.hello_spring_boot.dto.response.UserResponse;
import com.sam.hello_spring_boot.entity.User;
import com.sam.hello_spring_boot.exception.AppException;
import com.sam.hello_spring_boot.exception.ErrorCode;
import com.sam.hello_spring_boot.mapper.UserMapper;
import com.sam.hello_spring_boot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor // thay cho autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // cac bien k khai bao thi mac dinh private final
public class UserService {
     UserRepository userRepository; //cac Service chi dc goi xuong Repository

     UserMapper userMapper; //

    public User createUser(UserCreationRequest request){


        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_ENUM); // Kiem tra user co ton tai hay k
        User user = userMapper.toUser(request); //map request vao user

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

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //lay ra 1 user
    public UserResponse getUser(String id){
        return  userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not Found")));
    }


 }
