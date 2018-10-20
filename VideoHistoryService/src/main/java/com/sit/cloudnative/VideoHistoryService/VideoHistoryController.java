package com.sit.cloudnative.VideoHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VideoHistoryController {
    @Autowired
    VideoHistoryService videoHistoryService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/users/{userId}/video-history"
    )
    public ResponseEntity<VideoHistory> createNewHistory(
            @PathVariable("userId")int userId,
            @RequestParam("video_id")int videoId)
    {
        VideoHistory videoHistory = new VideoHistory(new CompositePrimaryKey(userId,videoId));
        videoHistory = videoHistoryService.createNewHistory(videoHistory);
        return new ResponseEntity<VideoHistory>(videoHistory,HttpStatus.CREATED);
    }
}
