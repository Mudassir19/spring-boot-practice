package com.practice.boot.repository;

import com.practice.boot.dto.StudentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentRequest,String> {
    List<StudentRequest> findByStudName(String studName);
}
