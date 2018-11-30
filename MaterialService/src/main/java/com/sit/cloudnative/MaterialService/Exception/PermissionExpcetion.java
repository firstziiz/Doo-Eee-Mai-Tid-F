package com.sit.cloudnative.MaterialService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class PermissionExpcetion extends HttpErrorException {
  public PermissionExpcetion() {
  }

  public PermissionExpcetion(String message) {
    super(message);
  }

  public PermissionExpcetion(String message, Throwable cause) {
    super(message, cause);
  }

  public PermissionExpcetion(Throwable cause) {
    super(cause);
  }

  public PermissionExpcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
