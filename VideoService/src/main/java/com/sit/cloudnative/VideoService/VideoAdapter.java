package com.sit.cloudnative.VideoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoAdapter {

  @Value("${nge.api.path}")
    private String api_Nge;

  public Video getVideoById(Long videoId){
    try {
      RestTemplate restTemplate = new RestTemplate();
      String url = String.format(api_Nge + "/api/v0/video/%d", videoId);
      Video video = restTemplate.getForObject(url, Video.class);
      return video;
    } catch (Exception ex ) {
      ex.printStackTrace();
      return null;
    }
  }
}