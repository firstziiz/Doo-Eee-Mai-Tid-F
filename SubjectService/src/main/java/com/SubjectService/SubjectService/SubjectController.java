package com.SubjectService.SubjectService;

import java.util.List;

import com.SubjectService.Logger.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
public class SubjectController{

    AuditLogger logger = new AuditLogger(this.getClass().getName());

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ProgramService programService;

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/subject/{subject_id}" 
    )
    public ResponseEntity<Subject> getSubject(@PathVariable("subject_id") int subject_id, HttpServletRequest request){
        logger.info(request, "Response subject: " + subject_id);
        return new ResponseEntity<Subject>(subjectService.getSubjectById(subject_id),HttpStatus.OK);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/program/{program_id}/subjects"
    )
    public ResponseEntity<List<Subject>> getAllSubject(@PathVariable("program_id") int program_id, HttpServletRequest request){
        logger.info(request,"Response program: " + program_id);
        return new ResponseEntity<List<Subject>> (subjectService.getAllSubjectByProgram(program_id), HttpStatus.OK);
    }  
    
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/programs"
    )
    public ResponseEntity<List<Program>> getAllProgram(HttpServletRequest request){
        logger.info(request, "Response all program in system");
        return new ResponseEntity<List<Program>> (programService.getAllProgram(), HttpStatus.OK);
    }

}