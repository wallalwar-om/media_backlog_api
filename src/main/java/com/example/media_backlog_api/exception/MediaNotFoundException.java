package com.example.media_backlog_api.exception;

public class MediaNotFoundException extends RuntimeException{

    public MediaNotFoundException(String message) {
        super(message);
    }
}
