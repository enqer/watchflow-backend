package com.example.watchflow.dto.mapper;

import com.example.watchflow.dto.UserDto;
import com.example.watchflow.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {


    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getEmail()
        );
    }
}
