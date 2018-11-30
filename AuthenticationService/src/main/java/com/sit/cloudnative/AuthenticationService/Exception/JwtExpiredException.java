package com.sit.cloudnative.AuthenticationService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtExpiredException extends HttpException {
  public JwtExpiredException() {
  }

  public JwtExpiredException(String message) {
    super(message);
  }

  public JwtExpiredException(String message, Throwable cause) {
    super(message, cause);
  }

  public JwtExpiredException(Throwable cause) {
    super(cause);
  }

  public JwtExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
