package com.example.media_backlog_api.service.serviceImplTest;
import com.example.media_backlog_api.dto.MediaItemRequestDTO;
import com.example.media_backlog_api.entity.MediaItem;
import com.example.media_backlog_api.repository.MediaRepository;
import com.example.media_backlog_api.service.serviceImpl.MediaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// 1. Tell JUnit to enable Mockito for this test class
@ExtendWith(MockitoExtension.class)
class MediaServiceImplTest {

    // 2. Create a "fake" database repository
    @Mock
    private MediaRepository mediaRepository;

    // 3. Create the real Service, and inject the fake repository into its constructor!
    @InjectMocks
    private MediaServiceImpl mediaService;

    @Test
    void addMedia_ShouldSetCompletedToFalseAndSave() {
        // --- 1. ARRANGE (Set up the data and the mock) ---
        MediaItemRequestDTO requestDTO = new MediaItemRequestDTO();
        requestDTO.setTitle("The Matrix");
        requestDTO.setType("Movie");

        MediaItem savedItem = new MediaItem();
        savedItem.setId(1L);
        savedItem.setTitle("The Matrix");
        savedItem.setType("Movie");
        savedItem.setCompleted(false);

        // Tell the fake database: "When anyone calls save() with ANY MediaItem, return this savedItem"
        Mockito.when(mediaRepository.save(ArgumentMatchers.any(MediaItem.class))).thenReturn(savedItem);

        // --- 2. ACT (Run the actual method we want to test) ---
        MediaItem result = mediaService.addItem(requestDTO);

        // --- 3. ASSERT (Check if the result matches our expectations) ---
        assertNotNull(result);
        assertEquals("The Matrix", result.getTitle());
        assertFalse(result.isCompleted()); // Proves our business logic worked!

        // Verify that the repository's save method was called exactly one time
        Mockito.verify(mediaRepository, Mockito.times(1)).save(ArgumentMatchers.any(MediaItem.class));
    }


    @Test
    void findById_ShouldThrowException_WhenItemNotFound() {

        // ARRANGE
        Long badId = 999L;
        // Tell the fake database: "If someone searches for 999, return an empty result"
        Mockito.when(mediaRepository.findById(badId)).thenReturn(Optional.empty());

        // ACT & ASSERT
        // We assert that calling findById(999) will trigger a RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mediaService.findById(badId);
        });

        // We can even assert that the error message is perfectly formatted
        assertEquals("Media not found!", exception.getMessage());

        // Verify the database was checked
        Mockito.verify(mediaRepository, Mockito.times(1)).findById(badId);
    }
}

