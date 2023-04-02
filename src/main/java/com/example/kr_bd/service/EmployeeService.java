package com.example.kr_bd.service;

import lombok.RequiredArgsConstructor;
import com.example.kr_bd.model.AuthRequest;
import com.example.kr_bd.model.Employee;
import com.example.kr_bd.respository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Boolean authUser(AuthRequest authRequest){
        Employee user = employeeRepository.getByEmail(authRequest.getLogin());
        if(user == null)
            return false;
        Boolean result = false;
        if(user.getPassword().equals(authRequest.getPassword()))
            result = true;

        return result;
    }
}
