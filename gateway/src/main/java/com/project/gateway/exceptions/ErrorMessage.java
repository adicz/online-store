package com.project.gateway.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class ErrorMessage {
    public String status;
    public String message;
    public LocalDateTime timestamp;
}
