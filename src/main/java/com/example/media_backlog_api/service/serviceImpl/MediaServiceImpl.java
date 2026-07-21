package com.example.media_backlog_api.service.serviceImpl;

import com.example.media_backlog_api.dto.MediaItemRequestDTO;
import com.example.media_backlog_api.entity.MediaItem;
import com.example.media_backlog_api.exception.MediaNotFoundException;
import com.example.media_backlog_api.repository.MediaRepository;
import com.example.media_backlog_api.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Spring registers THIS implementation as a bean
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Override
    public List<MediaItem> getAllMedia() {
        return mediaRepository.findAll();
    }

    @Override
    public MediaItem addItem(MediaItemRequestDTO dto) {
        MediaItem item = new MediaItem();
        item.setTitle(dto.getTitle());
        item.setType(dto.getType());
        item.setCompleted(false);

        return mediaRepository.save(item);
    }

    @Override
    public MediaItem findById(Long id) {

        // If it finds the item, it returns it.
        // If not, it throws our custom exception, which gets caught by our GlobalExceptionHandler!
        return mediaRepository.findById(id)
                .orElseThrow(() -> new MediaNotFoundException("Media not found!"));
    }
}
