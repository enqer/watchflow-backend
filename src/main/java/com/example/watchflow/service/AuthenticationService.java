package com.example.watchflow.service;


import com.example.watchflow.model.Role;
import com.example.watchflow.model.User;
import com.example.watchflow.repository.UserRepository;
import com.example.watchflow.security.AuthenticationRequest;
import com.example.watchflow.security.AuthenticationRespone;
import com.example.watchflow.security.JwtService;
import com.example.watchflow.security.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.text.ParseException;


@Service
@AllArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager manager;



    public AuthenticationRespone register(RegisterRequest request) throws ParseException {
        if (!isLoginValid(request.getLogin()) || !isEmailValid(request.getEmail()))
            return null;

        var user = User.builder()
                .login(request.getLogin())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationRespone.builder()
                .token(jwtToken)
                .build();
    }



    public AuthenticationRespone authenticate(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByLogin(request.getLogin())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationRespone.builder()
                .token(jwtToken)
                .build();
    }



    boolean  isLoginValid(String login){
        return !userRepository.isUserExists(login);
    }
    private boolean isEmailValid(String email) {
        return !userRepository.isUserExistsByEmail(email);
    }



}
