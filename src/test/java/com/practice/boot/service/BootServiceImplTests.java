package com.practice.boot.service;

import com.practice.boot.dto.StudentRequest;
import com.practice.boot.repository.StudentRepository;
import com.practice.boot.service.impl.BootServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BootServiceImplTests.class})
// Referenc: SDET- QA Automation Techie
public class BootServiceImplTests {

    @Mock
    StudentRepository repository;

    @InjectMocks
    BootServiceImpl service;

    @Test
    public void test_getAllStudents() {

        List<StudentRequest> studentList = new ArrayList<>();
        studentList.add(new StudentRequest("101", "Mahad", "5th", "A"));
        studentList.add(new StudentRequest("102", "Faiz", "7th", "B"));
        when(repository.findAll()).thenReturn(studentList); //mocking
        assertEquals(2, service.getAllStudents().size());
    }

    @Test
    public void test_getStudentByName() {
        String studName = "Mahad";
        List<StudentRequest> studentList = new ArrayList<>();
        studentList.add(new StudentRequest("101", "Mahad", "5th", "A"));
        when(repository.findByStudName(studName)).thenReturn(studentList);
        assertEquals(studName, service.getStudentByName(studName).get(0).getStudName());
    }

    @Test
    public void test_createStudent() {
        StudentRequest request = new StudentRequest("201", "Ayeza", "1st", "B");
        when(repository.save(request)).thenReturn(request);
        assertEquals(request, service.createStudent(request));
    }

    @Test
    public void test_updateStudent() {
        StudentRequest request = new StudentRequest("201", "Ayeza", "1st", "B");

        // given(repository.findById("201")).willReturn(Optional.of(request));
        Mockito.when(repository.findById("201")).thenReturn(Optional.of(request));
        request.setStudName("Mahad");
        service.updateStudent(request);
        assertThat(request.getStudName()).isEqualTo("Mahad");
    }

    @Test
    public void deleteById() {
        StudentRequest request = new StudentRequest("101", "Zobiya", "2nd", "C");
        Mockito.when(repository.findById("101")).thenReturn(Optional.of(request));
        service.deleteById("101");
        verify(repository, times(1)).deleteById("101");
        assertEquals("Record deleted successfully", service.deleteById("101"));
    }

}
