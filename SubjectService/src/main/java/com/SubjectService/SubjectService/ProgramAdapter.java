package com.SubjectService.SubjectService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProgramAdapter {

    public List<Program> getAllProgram(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String endpointProgramAPI = String.format("https://ngelearning.sit.kmutt.ac.th/api/v0/program/");
            List<Program> programs = restTemplate.getForObject(endpointProgramAPI, List.class);
            return programs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}