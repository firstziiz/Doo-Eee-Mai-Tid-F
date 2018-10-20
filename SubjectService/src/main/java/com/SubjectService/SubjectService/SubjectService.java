package com.SubjectService.SubjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SubjectService{

    @Autowired
    private SubjectAdapter dataForSubjectAdapter;
   
    public Subject getSubjectById(int subject_Id){
        return dataForSubjectAdapter.getSubjectById(subject_Id);
    }

    public List<Subject> getAllSubject() {
		return dataForSubjectAdapter.getAllSubject();
    }

}