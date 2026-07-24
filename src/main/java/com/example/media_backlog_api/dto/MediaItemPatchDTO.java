package com.example.media_backlog_api.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MediaItemPatchDTO {

    @Size(min = 2, max = 100, message = "If provided, title must be between 2 and 100 characters")
    private String title;

    private String type;
}
