package edu.project3;

import java.time.LocalDateTime;

public record LogEntry(String ipAddress, LocalDateTime timestamp, String requestLine, HttpStatus httpStatus,
                       long responseSize, String referrer, String userAgent) {
}
