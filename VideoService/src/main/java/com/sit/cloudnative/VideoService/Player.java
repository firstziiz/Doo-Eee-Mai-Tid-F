package com.sit.cloudnative.VideoService;

import com.fasterxml.jackson.annotation.JsonProperty;

class Player {
  // @JsonProperty("hls_url")
  private String url;
  // @JsonProperty("spritesheet_frame")
  private String spritesheet_frame;
  // @JsonProperty("spritesheet_file")
  private String spritesheet_file;
  // @JsonProperty("subtitle_available")
  private int subtitle_available;
  // @JsonProperty("subtitle_file")
  private String subtitle_file;

  public Player() {

  }

  public Player(
    @JsonProperty("hls_url") String url,
    @JsonProperty("spritesheet_frame") String spritesheet_frame,
    @JsonProperty("spritesheet_file") String spritesheet_file,
    @JsonProperty("subtitle_available") int subtitle_available,
    @JsonProperty("subtitle_file") String subtitle_file
  ) {
    this.url = url;
    this.spritesheet_frame = spritesheet_frame;
    this.spritesheet_file = spritesheet_file;
    this.subtitle_available = subtitle_available;
    this.subtitle_file = subtitle_file;
  }

  /**
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return the spritesheet_frame
   */
  public String getSpritesheet_frame() {
    return spritesheet_frame;
  }

  /**
   * @param spritesheet_frame the spritesheet_frame to set
   */
  public void setSpritesheet_frame(String spritesheet_frame) {
    this.spritesheet_frame = spritesheet_frame;
  }

  /**
   * @return the spritesheet_file
   */
  public String getSpritesheet_file() {
    return spritesheet_file;
  }

  /**
   * @param spritesheet_file the spritesheet_file to set
   */
  public void setSpritesheet_file(String spritesheet_file) {
    this.spritesheet_file = spritesheet_file;
  }

  /**
   * @return the subtitle_available
   */
  public int getSubtitle_available() {
    return subtitle_available;
  }

  /**
   * @param subtitle_available the subtitle_available to set
   */
  public void setSubtitle_available(int subtitle_available) {
    this.subtitle_available = subtitle_available;
  }

  /**
   * @return the subtitle_file
   */
  public String getSubtitle_file() {
    return subtitle_file;
  }

  /**
   * @param subtitle_file the subtitle_file to set
   */
  public void setSubtitle_file(String subtitle_file) {
    this.subtitle_file = subtitle_file;
  }
}