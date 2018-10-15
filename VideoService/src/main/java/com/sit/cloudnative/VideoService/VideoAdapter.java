package com.sit.cloudnative.VideoService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoAdapter {

  public Video getVideoById(Long videoId){
    try {
      RestTemplate restTemplate = new RestTemplate();
      String url = String.format("https://ngelearning.sit.kmutt.ac.th/api/v0/video/%d", videoId);
      Video video = restTemplate.getForObject(url, Video.class);
      return video;
    } catch (Exception ex ) {
      ex.printStackTrace();
      return null;
    }
  }
}