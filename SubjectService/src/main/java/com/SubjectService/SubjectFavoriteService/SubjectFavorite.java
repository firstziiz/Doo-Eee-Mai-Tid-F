package com.SubjectService.SubjectFavoriteService;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "subject_favorites")
public class SubjectFavorite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int subjectId;
    private String userId;

    @CreationTimestamp
    private Date createdAt;

    public SubjectFavorite() {
    }

    public SubjectFavorite(int subjectId, String userId) {
        this.subjectId = subjectId;
        this.userId = userId;
    }

    public SubjectFavorite(int id, int subjectId, String userId, Date createdAt) {
        this.id = id;
        this.subjectId = subjectId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
