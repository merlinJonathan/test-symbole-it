package com.example.services;

import com.example.Model.ExcuseModel;
import com.example.dto.ExcuseDTO;
import com.example.repositories.ExcuseFirestoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ExcuseService {
    @Autowired
    private ExcuseFirestoreRepository excuseFirestoreRepository;

    public ExcuseDTO getExcuseById(String id) throws ExecutionException, InterruptedException {
        ExcuseModel excuseModel = this.excuseFirestoreRepository.getExcuseByIdWithFireStore(id);

        return excuseModel != null ? excuseModel.toDTO() : null;
    }

    public List<ExcuseDTO> getAllExcuse() throws ExecutionException, InterruptedException {
       List<ExcuseModel> excuseModelList = this.excuseFirestoreRepository.getAllExcuseWithFireStore();

       if(excuseModelList == null) {
           return null;
       }

        return excuseModelList.stream().map(ExcuseModel::toDTO).collect(Collectors.toList());
    }

    public ExcuseDTO post(ExcuseDTO excuseDTO) throws ExecutionException, InterruptedException {
        ExcuseModel excuseModel = this.excuseFirestoreRepository.postWithFirestore(excuseDTO);

        return excuseModel != null ? excuseModel.toDTO() : null;
    }
}
