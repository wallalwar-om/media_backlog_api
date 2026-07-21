package com.example.media_backlog_api.service;

import com.example.media_backlog_api.dto.MediaItemRequestDTO;
import com.example.media_backlog_api.entity.MediaItem;

import java.util.List;

public interface MediaService {

    List<MediaItem> getAllMedia();
    MediaItem addItem(MediaItemRequestDTO dto);
    MediaItem findById(Long id);
}
