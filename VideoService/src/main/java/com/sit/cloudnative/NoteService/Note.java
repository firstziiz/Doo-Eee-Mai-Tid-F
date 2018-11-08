package com.sit.cloudnative.NoteService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sit.cloudnative.Audit.AuditModel;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "notes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EnableJpaAuditing
public class Note extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    private Long id;

    @NotNull
    private Long userId;


    @NotNull
    private Long videoId;

    @Lob
    private String content;

    public Note() {
    }

    public Note(@NotNull Long userId, @NotNull Long videoId, String content) {
        this.userId = userId;
        this.videoId = videoId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}