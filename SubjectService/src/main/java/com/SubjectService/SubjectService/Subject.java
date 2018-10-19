package com.SubjectService.SubjectService;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Subject implements Serializable{

    @Id
    private int subject_Id;
    private String subject_Name;
    private String subject_Code;
    private String subject_Description;

    public Subject(
        @JsonProperty("subject_id")int subject_Id,
        @JsonProperty("subject_name")String subject_Name,
        @JsonProperty("subject_code")String subject_Code,
        @JsonProperty("subject_description")String subject_Description
        ){
        this.subject_Id = subject_Id;
        this.subject_Name = subject_Name;
        this.subject_Code = subject_Code;
        this.subject_Description = subject_Description;

    }
   
    public int getSubject_Id() {
        return subject_Id;
    }

   
    public String getSubject_Description() {
        return subject_Description;
    }

   
    public void setSubject_Description(String subject_Description) {
        this.subject_Description = subject_Description;
    }

    
    public String getSubject_Code() {
        return subject_Code;
    }

    
    public void setSubject_Code(String subject_Code) {
        this.subject_Code = subject_Code;
    }

    
    public String getSubject_Name() {
        return subject_Name;
    }

    
    public void setSubject_Name(String subject_Name) {
        this.subject_Name = subject_Name;
    }

    
    public void setSubject_Id(int subject_Id) {
        this.subject_Id = subject_Id;
    }
}