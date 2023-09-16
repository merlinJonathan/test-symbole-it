package com.example.services;

import com.example.Model.ExcuseModel;
import com.example.dto.ExcuseDTO;
import com.example.repositories.ExcuseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ExcuseDTO> getAllExcuse() {
        return this.excuseRepository.findAll().stream()
                .map(ExcuseModel::getDTO).
                collect(Collectors.toList());
    }

    public ExcuseDTO post(ExcuseDTO excuseDTO) {
        ExcuseModel excuseModel = new ExcuseModel(excuseDTO.getMessage(), excuseDTO.getHttpCode(), excuseDTO.getTag());
        ExcuseModel excuseModelCreated = this.excuseRepository.save(excuseModel);
        return excuseModelCreated.getDTO();
    }
}
