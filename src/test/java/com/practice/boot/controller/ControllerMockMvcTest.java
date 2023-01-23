package com.practice.boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.boot.dto.StudentRequest;
import com.practice.boot.resource.BootController;
import com.practice.boot.service.BootService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ControllerMockMvcTest.class})
// Referenc: SDET- QA Automation Techie
public class ControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;


    @Mock
    BootService service;

    @InjectMocks
    BootController controller;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        //mockMvc = MockMvcBuilders.standaloneSetup(new BootController()).build();
    }

    @Test
    public void test_getAllStudent() throws Exception {
        List<StudentRequest> studList = new ArrayList<>();
        studList.add(new StudentRequest("101", "Mahad", "1st", "A"));
        studList.add(new StudentRequest("201", "Faiz", "5th", "A"));

        when(service.getAllStudents()).thenReturn(studList);
        this.mockMvc.perform(get("/student/all"))
                .andExpect(status().isFound())
                .andDo(print());
    }

    @Test
    public void test_getStudentByName() throws Exception {
        List<StudentRequest> studList = new ArrayList<>();
        String studName = "Mahad";
        studList.add(new StudentRequest("101", "Mahad", "1st", "A"));
        when(service.getStudentByName(studName)).thenReturn(studList);

        // this.mockMvc.perform(get("/student/name").param("studName","Mahad")) this is used in case of request parameter
        this.mockMvc.perform(get("/student/name/{studName}", studName))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath(".studId").value("101"))
                .andExpect(MockMvcResultMatchers.jsonPath(".studName").value("Mahad"))
                .andExpect(MockMvcResultMatchers.jsonPath(".studClass").value("1st"))
                .andDo(print());
    }

    @Test
    public void test_createStudent() throws Exception {
        StudentRequest request = new StudentRequest("101", "Faiz", "A", "5th");
        when(service.createStudent(request)).thenReturn(request);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(request);

        this.mockMvc.perform(post("/student")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void test_updateStudent() throws Exception {
        StudentRequest request = new StudentRequest("101", "Faiz", "A", "5th");
        when(service.updateStudent(request)).thenReturn(request);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(request);
        System.out.println("*****json body ***********" + jsonBody);

        this.mockMvc.perform(put("/student")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void test_deleteRecordById() throws Exception {
        StudentRequest request = new StudentRequest("101", "Mahad", "5th", "A");
        String studId = "1010";
        String msg = "Record deleted successfully";
        when(service.deleteById(studId)).thenReturn("Record deleted successfully");
        this.mockMvc.perform(delete("/student").param("studId", "101"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
