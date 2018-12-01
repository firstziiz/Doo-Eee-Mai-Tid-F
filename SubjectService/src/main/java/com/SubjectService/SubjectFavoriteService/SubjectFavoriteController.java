package com.SubjectService.SubjectFavoriteService;

import com.SubjectService.Exception.DuplicateSubjectFavorite;
import com.SubjectService.Logger.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.SubjectService.SubjectService.Subject;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
public class SubjectFavoriteController {

    @Autowired
    private SubjectFavoriteService subjectFavoriteService;

    AuditLogger logger = new AuditLogger(this.getClass().getName());

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/subject_favorites"
    )
    public ResponseEntity<SubjectFavorite> addSubjectFavorite(@RequestParam("subjectId") int subjectId, @RequestAttribute("userId") String userId, HttpServletRequest request){
        logger.info(request, "Add subject " + subjectId + " to favorite" + "by user: " + userId);
        SubjectFavorite subjectFavorite = null;
        try {
            subjectFavorite = subjectFavoriteService.addSubjectFavorite(subjectId, userId);
        } catch (DuplicateSubjectFavorite e) {
            logger.error(request, userId + " add duplicate subject");
        }
        return new ResponseEntity<SubjectFavorite>(subjectFavorite, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/subject_favorites"
    )
    public ResponseEntity<List<Subject>> getSubjectFavorite(@RequestAttribute("userId") String userId, HttpServletRequest request){
        logger.info(request, "Getting favorite subject of user: "  + userId);
        return new ResponseEntity<List<Subject>>(subjectFavoriteService.getSubjectFavoriteByUserId(userId), HttpStatus.OK);
    }
}
