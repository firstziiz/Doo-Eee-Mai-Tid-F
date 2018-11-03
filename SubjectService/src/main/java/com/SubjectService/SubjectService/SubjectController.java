package com.SubjectService.SubjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SubjectController{

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/subject/{subject_id}" 
    )
    public ResponseEntity<Subject> getSubject(@PathVariable("subject_id") int subject_id ){
        return new ResponseEntity<Subject>(subjectService.getSubjectById(subject_id),HttpStatus.OK);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/subjects"
    )
    public ResponseEntity<List<Subject>> getAllSubject(){        
        return   new ResponseEntity<List<Subject>> (subjectService.getAllSubject(), HttpStatus.OK);
    }  

}