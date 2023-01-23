package com.practice.boot.resource;

import com.practice.boot.dto.StudentRequest;
import com.practice.boot.service.BootService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class BootController {

    private static final Logger logger = LoggerFactory.getLogger(BootController.class);

    @Autowired
    BootService service;

    @GetMapping(value = "/hello")
    public String getHello() {
        logger.info("inside getHello controller::");
        return "hello program executed successfully:";
    }

    @PostMapping
    public ResponseEntity<StudentRequest> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        logger.info("Inside create student controller");
        StudentRequest response = service.createStudent(studentRequest);
        logger.info("response: " + response);
        return new ResponseEntity(response, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<StudentRequest>> getAllStudent() {
        logger.info("inside get all student controller");
        List<StudentRequest> response = service.getAllStudents();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/name/{studName}")
    public ResponseEntity<List<StudentRequest>> getStudentByName(@PathVariable String studName) {
        System.out.println("Student Name is:" + studName);

        List<StudentRequest> response = service.getStudentByName(studName);

        return new ResponseEntity(response, new HttpHeaders(), HttpStatus.FOUND);

    }

  /*  @GetMapping(value = "/name")
    public ResponseEntity<StudentRequest> getStudentByName(@RequestParam String studName) {
        System.out.println("Student Name is:" +studName);
        return null;
    }*/

    @DeleteMapping
    public ResponseEntity<String> deleteRecordById(@RequestParam String studId) {
        logger.info("inside delete record by id");
        String msg = service.deleteById(studId);
        return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<StudentRequest> updateStudent(@RequestBody StudentRequest studentRequest) {
        logger.info("inside updateStudent ");
        StudentRequest response = service.updateStudent(studentRequest);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    // update employee Name
    @PatchMapping("/{id}/{name}")
    public ResponseEntity<StudentRequest> updateStudentByName(@PathVariable String id, @PathVariable String name) {
        logger.info("in update student by name:");
        StudentRequest response=service.updateStudentByName(id,name);
        return new ResponseEntity<>(response, new HttpHeaders(),HttpStatus.OK);
    }

}
