package com.sit.cloudnative.VideoHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VideoHistoryController {
    @Autowired
    VideoHistoryService videoHistoryService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/users/{userId}/video-history"
    )
    public ResponseEntity<CompositePrimaryKey> createNewHistory(
            @PathVariable("userId")int userId,
            @RequestParam("video_id")int videoId)
    {
        VideoHistory videoHistory = new VideoHistory(new CompositePrimaryKey(userId,videoId));
        videoHistory = videoHistoryService.createNewHistory(videoHistory);
        CompositePrimaryKey compositePrimaryKey = videoHistory.getId();
        return new ResponseEntity<CompositePrimaryKey>(compositePrimaryKey,HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/{userId}/video-history"
    )
    public ResponseEntity<List<CompositePrimaryKey>> getHistory(@PathVariable("userId")int userId){
        List<VideoHistory> videoHistories = videoHistoryService.getUserHistory(userId);
        List<CompositePrimaryKey> compositePrimaryKeys = new ArrayList<>();
        for (VideoHistory videoHistory:videoHistories){
            CompositePrimaryKey compositePrimaryKey = videoHistory.getId();
            compositePrimaryKeys.add(compositePrimaryKey);
        }
        return new ResponseEntity<List<CompositePrimaryKey>>(compositePrimaryKeys,HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/users/{userId}/video-history"
    )
    public ResponseEntity deleteHistory(
            @PathVariable("userId")int userId,
            @RequestParam("video_id")int videoId)
    {
        VideoHistory videoHistory = new VideoHistory(new CompositePrimaryKey(userId,videoId));
        videoHistoryService.deleteUserHistory(videoHistory);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
