package com.sit.cloudnative.VideoHistoryService;

import com.sit.cloudnative.VideoHistoryService.Logger.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class VideoHistoryController {
    AuditLogger logger = new AuditLogger(this.getClass().getName());

    @Autowired
    VideoHistoryService videoHistoryService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/users/video-history"
    )
    public ResponseEntity<CompositePrimaryKey> createNewHistory(
            @RequestAttribute("userId") String userId,
            @RequestParam("video_id")int videoId,
            HttpServletRequest request
    )
    {
        VideoHistory videoHistory = new VideoHistory(new CompositePrimaryKey(Integer.parseInt(userId),videoId));
        videoHistory = videoHistoryService.createNewHistory(videoHistory);
        CompositePrimaryKey compositePrimaryKey = videoHistory.getId();
        logger.info(request, userId + " create new history with " + videoId);
        return new ResponseEntity<CompositePrimaryKey>(compositePrimaryKey,HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/video-history"
    )
    public ResponseEntity<List<CompositePrimaryKey>> getHistory(
            @RequestAttribute("userId") String userId,
            HttpServletRequest request){
        List<VideoHistory> videoHistories = videoHistoryService.getUserHistory(Integer.parseInt(userId));
        List<CompositePrimaryKey> compositePrimaryKeys = new ArrayList<>();
        for (VideoHistory videoHistory:videoHistories){
            CompositePrimaryKey compositePrimaryKey = videoHistory.getId();
            compositePrimaryKeys.add(compositePrimaryKey);
        }
        logger.info(request, userId + "get video history");
        return new ResponseEntity<List<CompositePrimaryKey>>(compositePrimaryKeys,HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/users/video-history"
    )
    public ResponseEntity<CompositePrimaryKey> deleteHistory(
            @RequestAttribute("userId") String userId,
            @RequestParam("video_id")int videoId,
            HttpServletRequest request)
    {
        CompositePrimaryKey id = new CompositePrimaryKey(Integer.parseInt(userId),videoId);
        VideoHistory videoHistory = videoHistoryService.getHistoryById(id);
        videoHistoryService.deleteUserHistory(videoHistory);
        logger.info(request, userId + "get video history with " + videoId);
        return new ResponseEntity<CompositePrimaryKey>(id,HttpStatus.NO_CONTENT);
    }

}
