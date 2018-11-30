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
            value = "/users/video-history"
    )
    public ResponseEntity<CompositePrimaryKey> createNewHistory(
            @RequestAttribute("userId") String userId,
            @RequestParam("video_id")int videoId)
    {
        VideoHistory videoHistory = new VideoHistory(new CompositePrimaryKey(Integer.parseInt(userId),videoId));
        videoHistory = videoHistoryService.createNewHistory(videoHistory);
        CompositePrimaryKey compositePrimaryKey = videoHistory.getId();
        return new ResponseEntity<CompositePrimaryKey>(compositePrimaryKey,HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/video-history"
    )
    public ResponseEntity<List<CompositePrimaryKey>> getHistory(@RequestAttribute("userId") String userId){
        List<VideoHistory> videoHistories = videoHistoryService.getUserHistory(Integer.parseInt(userId));
        List<CompositePrimaryKey> compositePrimaryKeys = new ArrayList<>();
        for (VideoHistory videoHistory:videoHistories){
            CompositePrimaryKey compositePrimaryKey = videoHistory.getId();
            compositePrimaryKeys.add(compositePrimaryKey);
        }
        return new ResponseEntity<List<CompositePrimaryKey>>(compositePrimaryKeys,HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/users/video-history"
    )
    public ResponseEntity deleteHistory(
            @RequestAttribute("userId") String userId,
            @RequestParam("video_id")int videoId)
    {
        CompositePrimaryKey id = new CompositePrimaryKey(Integer.parseInt(userId),videoId);
        VideoHistory videoHistory = videoHistoryService.getHistoryById(id);
        videoHistoryService.deleteUserHistory(videoHistory);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
