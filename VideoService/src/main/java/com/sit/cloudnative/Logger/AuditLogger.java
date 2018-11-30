package com.sit.cloudnative.Logger;

import com.savoirtech.logging.slf4j.json.logger.JsonLogger;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import com.savoirtech.logging.slf4j.json.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AuditLogger {

    private Logger logger;

    private final String USER_AGENT = "user-agent";
    private final String IP = "ip";

    public AuditLogger(String caller) {
        this.logger = LoggerFactory.getLogger(caller);
    }

    public void logging(HttpServletRequest request, String message, JsonLogger logger) {
        String userAgent = request.getHeader(USER_AGENT);
        String ip = request.getRemoteAddr();
        logger.message(message).field(USER_AGENT, userAgent).field(IP, ip).log();
    }

    public void warn(HttpServletRequest request, String message) {
        this.logging(request, message, this.logger.warn());
    }

    public void info(HttpServletRequest request, String message) {
        this.logging(request, message, this.logger.info());
    }

    public void error(HttpServletRequest request, String message) {
        this.logging(request, message, this.logger.error());
    }

    public void debug(HttpServletRequest request, String message) {
        this.logging(request, message, this.logger.debug());
    }
}
