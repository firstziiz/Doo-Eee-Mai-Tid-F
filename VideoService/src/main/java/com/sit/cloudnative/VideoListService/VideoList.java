package com.sit.cloudnative.VideoListService;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

public class VideoList implements Serializable {

  private String id;

  private String name;

  private String description;

  private String duration;

  private String date;

  private String starttime;
  
  private String endtime;

  private String thumbnail;

  public VideoList () {

  }

  public VideoList (
    @JsonProperty("video_id") String id,
    @JsonProperty("video_name") String name,
    @JsonProperty("video_description") String description,
    @JsonProperty("video_duration") String duration,
    @JsonProperty("video_date") String date,
    @JsonProperty("video_starttime") String starttime,
    @JsonProperty("video_endtime") String endtime,
    @JsonProperty("video_thumbnail") String thumbnail
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.duration = duration;
    this.date = date;
    this.starttime = starttime;
    this.endtime = endtime;
    this.thumbnail = thumbnail;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStarttime() {
    return starttime;
  }

  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }

  public String getEndtime() {
    return endtime;
  }

  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }
  
}