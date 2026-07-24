package com.example.media_backlog_api.controller;

import com.example.media_backlog_api.dto.MediaItemRequestDTO;
import com.example.media_backlog_api.entity.MediaItem;
import com.example.media_backlog_api.service.MediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Tells Spring this class returns raw data (JSON), not HTML views
@RequiredArgsConstructor
@RequestMapping("/api/media") // The base URL for all endpoints in this class
public class MediaController {

    private final MediaService mediaService;

    @GetMapping
    public List<MediaItem> getAllMedia() {
        return mediaService.getAllMedia(); // Fetches everything from the db
    }

    /*
    Yes, exactly. That is the magic of using the @RestController annotation.

    When you return a Java object (like your List<MediaItem>) from a @GetMapping method, Spring Boot intercepts it before
    it goes out over the network.
    Behind the scenes, Spring Boot uses a built-in library called Jackson. Jackson's entire job is to take your Java objects,
    read their properties (using those getters that Lombok generated for you), and automatically serialize them into standard JSON.
     */

    @PostMapping
    public MediaItem createMedia(@Valid @RequestBody MediaItemRequestDTO dto) {
        // Because of @Valid, Spring will check the DTO rules BEFORE running this code.
        // If the rules fail, this code never executes.
        // @RequestBody converts the incoming JSON into a MediaItem Java object
        return mediaService.addItem(dto);
    }

    // The "{id}" tells Spring to expect a dynamic value in the URL
    @GetMapping("/{id}")
    public MediaItem getById(@PathVariable Long id) {
        // @PathVariable extracts the {id} from the URL and injects it here
        return mediaService.findById(id);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {

        mediaService.deleteItem(id);
        // Returns a 204 No Content status code
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{/id}")
    public ResponseEntity<MediaItem> updateMedia(@PathVariable Long id, @Valid @RequestBody MediaItemRequestDTO updateDto) {
        MediaItem updatedItem = mediaService.updateItem(id, updateDto);
        return ResponseEntity.ok(updatedItem);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MediaItem> patchMedia(@PathVariable Long id, @Valid @RequestBody MediaItemRequestDTO patchDto) {
        MediaItem patchedItem = mediaService.updateItem(id, patchDto);
        return ResponseEntity.ok(patchedItem);
    }
}
