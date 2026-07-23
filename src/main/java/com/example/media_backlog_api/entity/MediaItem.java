package com.example.media_backlog_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Tells Spring: "Create an SQL table for this class"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaItem {

    @Id // Tells Spring: "This is a primary key"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tells H2 to auto-increment
    private long id;

    private String title;

    private String type;

    private boolean isCompleted;
}
