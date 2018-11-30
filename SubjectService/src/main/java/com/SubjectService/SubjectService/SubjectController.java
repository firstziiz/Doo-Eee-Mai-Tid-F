package com.SubjectService.SubjectService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private ProgramService programService;

    Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/subject/{subject_id}" 
    )
    public ResponseEntity<Subject> getSubject(@PathVariable("subject_id") int subject_id ){
        logger.info("Response subject: " + subject_id);
        return new ResponseEntity<Subject>(subjectService.getSubjectById(subject_id),HttpStatus.OK);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/program/{program_id}/subjects"
    )
    public ResponseEntity<List<Subject>> getAllSubject(@PathVariable("program_id") int program_id ){        
        logger.info("Response program: " + program_id);
        return new ResponseEntity<List<Subject>> (subjectService.getAllSubjectByProgram(program_id), HttpStatus.OK);
    }  
    
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/programs"
    )
    public ResponseEntity<List<Program>> getAllProgram(){
        logger.info("Response all program in system");
        return new ResponseEntity<List<Program>> (programService.getAllProgram(), HttpStatus.OK);
    }

}