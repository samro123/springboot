package com.sam.hello_spring_boot.controller;

import com.sam.hello_spring_boot.dto.request.ApiResponse;
import com.sam.hello_spring_boot.dto.request.UserCreationRequest;
import com.sam.hello_spring_boot.dto.request.UserUpdateRequest;
import com.sam.hello_spring_boot.entity.User;
import com.sam.hello_spring_boot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService; //Controller chi dc goi xuong service

    @PostMapping
    ApiResponse <User> createUser(@RequestBody @Valid UserCreationRequest request){
        //De map data tu request vao object
        ApiResponse<User>  apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }


}
