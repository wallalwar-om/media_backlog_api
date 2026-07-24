package com.example.media_backlog_api.service.serviceImpl;

import com.example.media_backlog_api.dto.MediaItemPatchDTO;
import com.example.media_backlog_api.dto.MediaItemRequestDTO;
import com.example.media_backlog_api.entity.MediaItem;
import com.example.media_backlog_api.exception.MediaNotFoundException;
import com.example.media_backlog_api.mapper.MediaMapper;
import com.example.media_backlog_api.repository.MediaRepository;
import com.example.media_backlog_api.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Spring registers THIS implementation as a bean
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    @Override
    public List<MediaItem> getAllMedia() {
        return mediaRepository.findAll();
    }

    /*
     * =====================================================================================
     * REFERENCE EXAMPLE: Throwing an exception when a list is empty.
     *
     * We cannot use .orElseThrow() here because findAll() returns a standard Java List,
     * not an Optional. If we strictly wanted to return a 404/ErrorResponse instead of an
     * empty array, we would have to manually check if the list is empty like this:
     * =====================================================================================
     *
     * List<MediaItem> mediaList = mediaRepository.findAll();
     * if (mediaList.isEmpty()) {
     *     throw new NoMediaAvailable("No Media Content Available in the database!");
     * }
     * return mediaList;
     */

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

    @Override
    public MediaItem updateItem(Long id, MediaItemRequestDTO updateDto) {
        MediaItem item = findById(id);

//        if(!Objects.equals(item.getTitle(), updateDto.getTitle())) {
//            item.setTitle(updateDto.getTitle());
//        }
//        if(!Objects.equals(item.getType(), updateDto.getType())) {
//            item.setType(updateDto.getType());
//        }

        mediaMapper.updateItemFromDto(updateDto, item);
        return mediaRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        MediaItem item = findById(id);
        mediaRepository.delete(item);
    }

    @Override
    public MediaItem patchItem(Long id, MediaItemPatchDTO patchDto) {
        MediaItem item = findById(id);
        mediaMapper.patchItemFromDto(patchDto, item);
        return mediaRepository.save(item);
    }
}
