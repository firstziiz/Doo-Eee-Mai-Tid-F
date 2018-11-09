package com.sit.cloudnative.VideoService;

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
public class VideoController {
  
  @Autowired
  private VideoService videoService;

  @CrossOrigin(origins = "*")
  @RequestMapping(
    method = RequestMethod.GET,
    value = "/video/{videoId}"
  )
  public ResponseEntity<Video> getVideo(@PathVariable("videoId") Long videoId) {
    return new ResponseEntity<Video>(videoService.getVideoById(videoId), HttpStatus.OK);
  }
}