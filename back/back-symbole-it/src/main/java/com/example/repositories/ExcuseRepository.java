package com.example.repositories;

import com.example.Model.ExcuseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcuseRepository extends JpaRepository<ExcuseModel, Long> {
}
