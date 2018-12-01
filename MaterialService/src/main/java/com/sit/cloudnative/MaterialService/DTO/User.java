package com.sit.cloudnative.MaterialService.DTO;

public class User {
  String userId;
  String role;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean canUploadMaterial() {
    return this.role.equals("TEACHER");
  }
}