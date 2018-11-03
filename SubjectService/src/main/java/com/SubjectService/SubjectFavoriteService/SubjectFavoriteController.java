package com.SubjectService.SubjectFavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SubjectFavoriteController {

    @Autowired
    private SubjectFavoriteService subjectFavoriteService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/subject/{subject_id}/user/{user_id}"
    )
    public ResponseEntity<SubjectFavorite> addSubjectFavorite(@PathVariable("subject_id") int subjectId, @PathVariable("subject_id") int userId){
        return new ResponseEntity<SubjectFavorite>(subjectFavoriteService.addSubjectFavorite(subjectId, userId), HttpStatus.CREATED);
    }
}
