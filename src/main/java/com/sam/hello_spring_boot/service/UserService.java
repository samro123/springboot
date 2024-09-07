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
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_ENUM);
        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not Found"));
        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not Found")));
    }


}
