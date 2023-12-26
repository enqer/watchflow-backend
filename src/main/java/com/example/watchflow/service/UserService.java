package com.example.watchflow.service;


import com.example.watchflow.dto.UserDto;
import com.example.watchflow.dto.mapper.UserDtoMapper;
import com.example.watchflow.model.User;
import com.example.watchflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;


    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(userDtoMapper).toList();
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .stream()
                .map(userDtoMapper)
                .toList().get(0);
    }
}
