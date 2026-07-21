package com.example.media_backlog_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime localDateTime;
    private int status;
    private String message;
    private Map<String, String> validationErrors; // Used to tell the frontend exactly which fields failed
}
