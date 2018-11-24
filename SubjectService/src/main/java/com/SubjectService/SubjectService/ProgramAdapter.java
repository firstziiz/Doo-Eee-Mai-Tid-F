package com.SubjectService.SubjectService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProgramAdapter {

    @Value("${nge.api.path}")
    String ngeApiPath;

    public List<Program> getAllProgram(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String endpointProgramAPI = String.format(ngeApiPath + "/api/v0/program/");
            List<Program> programs = restTemplate.getForObject(endpointProgramAPI, List.class);
            return programs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}