package com.practice.boot.service;

import com.practice.boot.dto.StudentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BootService {
    StudentRequest createStudent(StudentRequest studentRequest);

    List<StudentRequest> getAllStudents();

    List<StudentRequest> getStudentByName(String studName);

    String deleteById(String studId);

    StudentRequest updateStudent(StudentRequest studentRequest);

    StudentRequest updateStudentByName(String id, String name);
}
