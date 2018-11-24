package com.sit.cloudnative.VideoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoAdapter {

  @Value("${nge.api.path}")
  private String thirdPartyVideoServiceApiUrl;

  private Logger logger = LoggerFactory.getLogger(VideoAdapter.class);

  public Video getVideoById(Long videoId){
    try {
      RestTemplate restTemplate = new RestTemplate();
      String url = thirdPartyVideoServiceApiUrl+"/api/v0/video/"+videoId;
      logger.info("Requesting to "+url);
      Video video = restTemplate.getForObject(url, Video.class);
      return video;
    } catch (Exception ex ) {
      ex.printStackTrace();
      return null;
    }
  }
}