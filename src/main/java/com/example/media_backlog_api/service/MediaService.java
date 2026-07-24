package com.example.media_backlog_api.service;

import com.example.media_backlog_api.dto.MediaItemRequestDTO;
import com.example.media_backlog_api.entity.MediaItem;
import jakarta.validation.Valid;

import java.util.List;

public interface MediaService {

    List<MediaItem> getAllMedia();
    MediaItem addItem(MediaItemRequestDTO dto);
    MediaItem findById(Long id);

    MediaItem updateItem(Long id, MediaItemRequestDTO updateDto);

    void deleteItem(Long id);
}
