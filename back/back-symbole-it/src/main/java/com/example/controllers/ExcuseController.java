package com.example.controllers;

import com.example.dto.ExcuseDTO;
import com.example.services.ExcuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/excuse")
public class ExcuseController {
    @Autowired
    private ExcuseService excuseService;

    @GetMapping("/{id}")
    public ResponseEntity<ExcuseDTO> getExcuseById(@PathVariable Long id) {
        ExcuseDTO excuseDTO = this.excuseService.getExcuseById(id);

        if(excuseDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(excuseDTO);
    }
}
