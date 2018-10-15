package com.sit.cloudnative.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

  @Autowired
  private VideoAdapter videoAdapter;

  public Video getVideoById(Long videoId) {
    return videoAdapter.getVideoById(videoId);
  }
}