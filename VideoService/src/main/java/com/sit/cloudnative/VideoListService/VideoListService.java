package com.sit.cloudnative.VideoListService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoListService {

  @Autowired
  private VideoListAdapter videoListAdapter;

  public List<VideoList> getVideoById(Long subjectID) {
    return videoListAdapter.getVideoBySubject(subjectID);
  }
}