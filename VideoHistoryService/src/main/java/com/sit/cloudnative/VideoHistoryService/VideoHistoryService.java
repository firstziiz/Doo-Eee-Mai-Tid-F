package com.sit.cloudnative.VideoHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoHistoryService {
    @Autowired
    private VideoHistoryRepository videoHistoryRepository;

    public VideoHistory createNewHistory(VideoHistory videoHistory){
        return videoHistoryRepository.save(videoHistory);
    }
    public List<VideoHistory> getUserHistory(int userId){
        return videoHistoryRepository.findByIdUserId(userId);
    }
    public void deleteUserHistory(VideoHistory videoHistory){
        videoHistoryRepository.delete(videoHistory);
    }
}
