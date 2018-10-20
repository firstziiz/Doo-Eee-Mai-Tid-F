package com.sit.cloudnative.VideoHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoHistoryService {
    @Autowired
    private VideoHistoryRepository videoHistoryRepository;

    public VideoHistory createNewHistory(VideoHistory videoHistory){
        return videoHistoryRepository.save(videoHistory);
    }
}
