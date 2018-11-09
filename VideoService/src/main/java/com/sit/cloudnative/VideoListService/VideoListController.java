package com.sit.cloudnative.VideoListService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class VideoListController {
  
  @Autowired
  private VideoListService videoService;

  @RequestMapping(
    method = RequestMethod.GET,
    value = "/subject/{subjectId}/videos"
  )
  public ResponseEntity<List<?>> getVideoById(@PathVariable("subjectId") Long subjectID) {
    return new ResponseEntity<List<?>>(videoService.getVideoById(subjectID), HttpStatus.OK);
  }


}