package com.example.media_backlog_api.mapper;

import com.example.media_backlog_api.dto.MediaItemRequestDTO;
import com.example.media_backlog_api.entity.MediaItem;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MediaMapper {

    // MapStruct sees this and automatically generates the 20 'if' statements to update the item!
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemFromDto(MediaItemRequestDTO dto, @MappingTarget MediaItem item);
}
