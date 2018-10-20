package com.sit.cloudnative.VideoHistoryService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "video_histories")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","createdAt","updatedAt"})
public class VideoHistory implements Serializable {
    @EmbeddedId
    CompositePrimaryKey id;

    @Column(name = "created_at",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public VideoHistory() {
    }

    public VideoHistory(CompositePrimaryKey id) {
        this.id = id;
    }

    public CompositePrimaryKey getId() {
        return id;
    }

    public void setId(CompositePrimaryKey id) {
        this.id = id;
    }
}
@Embeddable
class CompositePrimaryKey implements Serializable{
    @Column(name = "user_id")
    private int userId;
    @Column(name = "video_id")
    private int videoId;

    public CompositePrimaryKey() {
    }

    public CompositePrimaryKey(int userId, int videoId) {
        this.userId = userId;
        this.videoId = videoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}