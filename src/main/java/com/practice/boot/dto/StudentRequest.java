package com.practice.boot.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="stud_table")
public class StudentRequest {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stud_id")
    @NotNull(message="student id cannot be null")
    private String studId;

    @Column(name="stud_name")
    @NotNull(message = "student name cannot be null")
    private String studName;
    @Column(name="stud_class")
    private String studClass;
    @Column(name="stud_div")
    private String studDiv;

    @Override
    public String toString() {
        return "StudentRequest{" +
                "studId='" + studId + '\'' +
                ", studName='" + studName + '\'' +
                ", studClass='" + studClass + '\'' +
                ", studDiv='" + studDiv + '\'' +
                '}';
    }

    public StudentRequest(String studId, String studName, String studClass, String studDiv) {
        this.studId = studId;
        this.studName = studName;
        this.studClass = studClass;
        this.studDiv = studDiv;
    }

    public StudentRequest() {

    }


    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudClass() {
        return studClass;
    }

    public void setStudClass(String studClass) {
        this.studClass = studClass;
    }

    public String getStudDiv() {
        return studDiv;
    }

    public void setStudDiv(String studDiv) {
        this.studDiv = studDiv;
    }
}
