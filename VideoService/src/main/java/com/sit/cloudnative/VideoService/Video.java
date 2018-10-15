package com.sit.cloudnative.VideoService;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "videos")
public class Video implements Serializable {

  @Id
  private String id;

  // @NotBlank
  private String name;

  // @NotBlank
  private String description;

  // @NotBlank
  private String duration;

  // @NotBlank
  private String date;

  // @NotBlank
  private String starttime;
  
  // @NotBlank
  private String endtime;

  // @NotBlank
  private String thumbnail;

  private Player player; 

  public Video () {

  }

  public Video (
    @JsonProperty("video_id") String id,
    @JsonProperty("video_name") String name,
    @JsonProperty("video_description") String description,
    @JsonProperty("video_duration") String duration,
    @JsonProperty("video_date") String date,
    @JsonProperty("video_starttime") String starttime,
    @JsonProperty("video_endtime") String endtime,
    @JsonProperty("video_thumbnail") String thumbnail,
    @JsonProperty("player") Player player
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.duration = duration;
    this.date = date;
    this.starttime = starttime;
    this.endtime = endtime;
    this.thumbnail = thumbnail;
    this.player = player;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the date
   */
  public String getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * @return the starttime
   */
  public String getStarttime() {
    return starttime;
  }

  /**
   * @param endtime the endtime to set
   */
  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }

  /**
   * @return the endtime
   */
  public String getEndtime() {
    return endtime;
  }

  /**
   * @param starttime the starttime to set
   */
  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }

  /**
   * @return the duration
   */
  public String getDuration() {
    return duration;
  }

  /**
   * @param duration the duration to set
   */
  public void setDuration(String duration) {
    this.duration = duration;
  }

  /**
   * @return the thumbnail
   */
  public String getThumbnail() {
    return thumbnail;
  }

  /**
   * @param thumbnail the thumbnail to set
   */
  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }
  
  /**
   * @return the player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * @param player the player to set
   */
  public void setPlayer(Player player) {
    this.player = player;
  }
}