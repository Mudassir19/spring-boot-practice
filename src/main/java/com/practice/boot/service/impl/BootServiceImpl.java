package com.practice.boot.service.impl;

import com.practice.boot.dto.StudentRequest;
import com.practice.boot.exception.RecordExist;
import com.practice.boot.exception.RecordNotFound;
import com.practice.boot.repository.StudentRepository;
import com.practice.boot.service.BootService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BootServiceImpl implements BootService {

    private static final Logger logger = LoggerFactory.getLogger(BootServiceImpl.class);

    @Autowired
    StudentRepository repository;

    @Override
    public StudentRequest createStudent(StudentRequest studentRequest) {
        logger.info("inside createStudent service impl:");
        Optional<StudentRequest> byId = repository.findById(studentRequest.getStudId());
        if (byId.isPresent()) {
            logger.info("record already available:");
            throw new RecordExist("Record exist for same id, pls use different id");
        } else {
            return repository.save(studentRequest);
        }

    }

    @Override
    public List<StudentRequest> getAllStudents() {
        logger.info("inside getAllStudents: service impl");
        List<StudentRequest> all = repository.findAll();
        if (!all.isEmpty()) {
            return all;
        } else {
            throw new RecordNotFound("RecordNot found");
        }
    }

    @Override
    public List<StudentRequest> getStudentByName(String studName) {
        logger.info("inside getStudentByName: service impl ");
        List<StudentRequest> byStudName = repository.findByStudName(studName);
        if (!byStudName.isEmpty()) {
            return byStudName;
        } else {
            throw new RecordNotFound("Record is not found for the name :" + studName);
        }
    }

    @Override
    public String deleteById(String studId) {
        logger.info("in deleteById Controller:");
        Optional<StudentRequest> byId = repository.findById(studId);
/*      repository.deleteById(studId);
       return "record deleted sucessfully";*/
        if (byId.isPresent()) {
            repository.deleteById(studId);
            return "Record deleted successfully";
        } else {
            return "Record is not available to delete";
        }
    }

    @Override
    public StudentRequest updateStudent(StudentRequest studentRequest) {
        logger.info("updateStudent: service impl");
        Optional<StudentRequest> byId = repository.findById(studentRequest.getStudId());
        if (byId.isPresent()) {
            StudentRequest request = new StudentRequest();
            request.setStudId(studentRequest.getStudId());
            request.setStudName(studentRequest.getStudName());
            request.setStudClass(studentRequest.getStudClass());
            request.setStudDiv(studentRequest.getStudDiv());
            StudentRequest save = repository.save(request);
            return save;
        } else {
            throw new RecordNotFound("Record is not available to update");
        }
    }

    @Override
    public StudentRequest updateStudentByName(String id, String name) {

        StudentRequest studentRequest = repository.findById(id).orElseThrow(() -> new RecordNotFound("User not found on ::" + id));
        studentRequest.setStudName(name);
        return repository.save(studentRequest);

    }
}