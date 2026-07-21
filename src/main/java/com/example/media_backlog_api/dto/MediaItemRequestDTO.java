package com.example.media_backlog_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MediaItemRequestDTO {

    // @NotBlank ensures it's not null, not empty, and not just whitespace
    @NotBlank(message = "Title is mandatory and cannot be blank")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Media type (e.g., Movie, Book) is required")
    private String type;
}
