package com.example.kr_bd.service;

import com.example.kr_bd.exception.KrBdException;
import com.example.kr_bd.model.AuthRequest;
import com.example.kr_bd.model.RegistrationRequest;
import com.example.kr_bd.model.User;
import com.example.kr_bd.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UserRepository userRepository;

    public Long authUser(AuthRequest authRequest){
        User user = userRepository.getByLogin(authRequest.getLogin());

        if(user == null || !user.getPassword().equals(authRequest.getPassword()))
            throw new KrBdException("Invalid login or password");

        return user.getId();
    }

    public void registration(RegistrationRequest registrationRequest){
        userRepository.insertUser(registrationRequest);
    }
}
