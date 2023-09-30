package com.example.controllers;

import com.example.dto.ExcuseDTO;
import com.example.contantes.ApiUrls;
import com.example.services.ExcuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(ApiUrls.EXCUSE)
public class ExcuseController {
    @Autowired
    private ExcuseService excuseService;

    @GetMapping("/{id}")
    public ResponseEntity<ExcuseDTO> getExcuseById(@PathVariable String id) throws ExecutionException, InterruptedException {
        ExcuseDTO excuseDTO = this.excuseService.getExcuseById(id);

        if(excuseDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(excuseDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<ExcuseDTO>> getAllExcuse() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(this.excuseService.getAllExcuse());
    }

    @PostMapping("")
    public ResponseEntity<ExcuseDTO> postExcuse(@RequestBody ExcuseDTO excuseDTO) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(this.excuseService.post(excuseDTO), HttpStatus.CREATED);
    }
}
