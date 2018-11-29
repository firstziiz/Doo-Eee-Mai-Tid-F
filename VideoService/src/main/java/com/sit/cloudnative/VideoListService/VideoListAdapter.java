package com.sit.cloudnative.VideoListService;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoListAdapter {

  @Value("${nge.api.path}")
    private String api_Nge;


  public List<VideoList> getVideoBySubject(Long subjectID){
    try {
      RestTemplate restTemplate = new RestTemplate();
      String url = String.format(api_Nge + "/api/v0/subject/%d/videos", subjectID);
      List<VideoList> videoList = restTemplate.getForEntity(url, List.class).getBody();
      return videoList;
    } catch (Exception ex ) {
      ex.printStackTrace();
      return null;
    }
  }
  
}