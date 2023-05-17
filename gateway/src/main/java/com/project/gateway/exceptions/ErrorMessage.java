package com.project.gateway.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class ErrorMessage {
    private String status;
    private String message;
    private LocalDateTime timestamp;
}
