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
            value = "/subject_favorites"
    )
    public ResponseEntity<SubjectFavorite> addSubjectFavorite(@PathVariable("subject_id") int subjectId, @RequestAttribute("userId") long userId){
        return new ResponseEntity<SubjectFavorite>(subjectFavoriteService.addSubjectFavorite(subjectId, userId), HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/subject_favorites"
    )
    public ResponseEntity<List<Subject>> getSubjectFavorite(@RequestAttribute("userId") long userId){
        return new ResponseEntity<List<Subject>>(subjectFavoriteService.getSubjectFavoriteByUserId(userId), HttpStatus.OK);
    }
}
