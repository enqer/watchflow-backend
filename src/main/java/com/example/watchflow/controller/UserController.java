package com.example.watchflow.controller;


import com.example.watchflow.dto.UserDto;
import com.example.watchflow.model.User;
import com.example.watchflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> userList = userService.getUsers();
        if (!userList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = userService.getUserById(id);
        if (userDto != null)
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
