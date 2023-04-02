package com.example.kr_bd.service;

import com.example.kr_bd.model.AuthRequest;
import com.example.kr_bd.model.User;
import com.example.kr_bd.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UserRepository userRepository;

    public Boolean authUser(AuthRequest authRequest){
        User user = userRepository.getByEmail(authRequest.getLogin());
        if(user == null)
            return false;
        Boolean result = false;
        if(user.getPassword().equals(authRequest.getPassword()))
            result = true;

        return result;
    }
}
