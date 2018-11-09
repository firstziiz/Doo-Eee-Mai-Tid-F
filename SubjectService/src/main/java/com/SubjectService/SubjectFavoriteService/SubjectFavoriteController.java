package com.SubjectService.SubjectFavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.SubjectService.SubjectService.Subject;

@RestController
@CrossOrigin("*")
public class SubjectFavoriteController {

    @Autowired
    private SubjectFavoriteService subjectFavoriteService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/subject_favorites/{subject_id}/user/{user_id}"
    )
    public ResponseEntity<SubjectFavorite> addSubjectFavorite(@PathVariable("subject_id") int subjectId, @PathVariable("subject_id") long userId){
        return new ResponseEntity<SubjectFavorite>(subjectFavoriteService.addSubjectFavorite(subjectId, userId), HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/subject_favorites/user/{user_id}"
    )
    public ResponseEntity<List<Subject>> getSubjectFavorite(@PathVariable("user_id") long userId){
        return new ResponseEntity<List<Subject>>(subjectFavoriteService.getSubjectFavoriteByUserId(userId), HttpStatus.OK);
    }
}
