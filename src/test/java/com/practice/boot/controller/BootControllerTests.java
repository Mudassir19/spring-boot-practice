package com.practice.boot.controller;

import com.practice.boot.dto.StudentRequest;
import com.practice.boot.resource.BootController;
import com.practice.boot.service.BootService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BootControllerTests.class})
// Referenc: SDET- QA Automation Techie
public class BootControllerTests {


    @Mock
    BootService service;

    @InjectMocks
    BootController controller;

    @Test
    public void test_getAllStudent() {
        List<StudentRequest> studList = new ArrayList<>();
        studList.add(new StudentRequest("101", "Mahad", "1st", "A"));
        studList.add(new StudentRequest("201", "Faiz", "5th", "A"));

        when(service.getAllStudents()).thenReturn(studList);

        ResponseEntity<List<StudentRequest>> response = controller.getAllStudent();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void test_getStudentByName() {
        List<StudentRequest> studList = new ArrayList<>();
        studList.add(new StudentRequest("101", "Mahad", "1st", "A"));
        when(service.getStudentByName("Mahad")).thenReturn(studList);
        ResponseEntity<List<StudentRequest>> response = controller.getStudentByName("Mahad");
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals("Mahad", response.getBody().get(0).getStudName());
    }

    @Test
    public void test_createStudent() {
        StudentRequest request = new StudentRequest();
        request.setStudId("1010");
        request.setStudName("Faiz");
        request.setStudDiv("A");
        request.setStudClass("5th");
        when(service.createStudent(request)).thenReturn(request);
        ResponseEntity<StudentRequest> response = controller.createStudent(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response);
    }

    @Test
    public void test_updateStudent() {
        StudentRequest request = new StudentRequest();
        request.setStudId("1010");
        request.setStudName("Mahad");
        request.setStudDiv("A");
        request.setStudClass("5th");
        when(service.updateStudent(request)).thenReturn(request);
        ResponseEntity<StudentRequest> response = controller.updateStudent(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Mahad", response.getBody().getStudName());
    }

    @Test
    public void test_deleteRecordById() {
        // StudentRequest request = new StudentRequest("101","Mahad","5th","A");
        String studId = "1010";
        when(service.deleteById(studId)).thenReturn("Record deleted successfully");
        ResponseEntity<String> response = controller.deleteRecordById(studId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Record deleted successfully", response.getBody());
    }
}
