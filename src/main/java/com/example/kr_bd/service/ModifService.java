package com.example.kr_bd.service;

import lombok.RequiredArgsConstructor;
import com.example.kr_bd.model.Modification;
import com.example.kr_bd.respository.ModifRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifService {
    private final ModifRepository modifRepository;

    public List<Modification> getAllModif(Boolean asc){
        return modifRepository.getAllModif(asc);
    }
}
