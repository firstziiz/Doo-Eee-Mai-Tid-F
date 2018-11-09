package com.SubjectService.SubjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProgramService {

    @Autowired
    private ProgramAdapter programAdapter;
   
    public List<Program> getAllProgram(){
        return programAdapter.getAllProgram();
    }

}