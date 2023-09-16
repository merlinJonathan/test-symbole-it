package com.example.services;

import com.example.Model.ExcuseModel;
import com.example.dto.ExcuseDTO;
import com.example.repositories.ExcuseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExcuseService {
    @Autowired
    private ExcuseRepository excuseRepository;

    public ExcuseDTO getExcuseById(long id) {
        Optional<ExcuseModel> excuseModel = this.excuseRepository.findById(id);
        if(excuseModel.isPresent()) {
            return excuseModel.get().getDTO();
        }

        return null;
    }
}
