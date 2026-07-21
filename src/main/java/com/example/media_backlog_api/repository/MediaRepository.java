package com.example.media_backlog_api.repository;

import com.example.media_backlog_api.entity.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaItem, Long> {

}

/*
Why this is powerful: By extending JpaRepository, Spring Boot automatically generates the code for standard database operations.
The <MediaItem, Long> tells Spring that this repository manages MediaItem entities, and its Primary Key (@Id) is a Long.
You instantly have access to methods like save(), findAll(), findById(), and deleteById()
*/