package com.sam.hello_spring_boot.service;

import com.sam.hello_spring_boot.dto.request.UserCreationRequest;
import com.sam.hello_spring_boot.dto.request.UserUpdateRequest;
import com.sam.hello_spring_boot.entity.User;
import com.sam.hello_spring_boot.exception.AppException;
import com.sam.hello_spring_boot.exception.ErrorCode;
import com.sam.hello_spring_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository; //cac Service chi dc goi xuong Repository

    public User createUser(UserCreationRequest request){
        User user = new User();

        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_ENUM);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public  User updateUser(String userId, UserUpdateRequest request){
        User user = getUser(userId);
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return  userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //lay ra 1 user
    public User getUser(String id){
        return  userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not Found"));
    }


 }
