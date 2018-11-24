package com.SubjectService.SubjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SubjectService{

    @Autowired
    private SubjectAdapter subjectAdapter;
   
    public Subject getSubjectById(int subject_Id){
        return subjectAdapter.getSubjectById(subject_Id);
    }

    public List<Subject> getAllSubjectByProgram(int program_id) {
		return subjectAdapter.getAllSubjectByProgram(program_id);
    }

}