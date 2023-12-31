package com.taskflow.taskmanagement.converters;

import com.taskflow.taskmanagement.dtos.tag.request.TagRequest;
import com.taskflow.taskmanagement.entities.Tag;

import java.util.List;

public class TagConverter {

    public static Tag convertToEntity(TagRequest tagRequest) {
        return Tag.builder()
                .id(null)
                .name(tagRequest.getName())
                .tasks(List.of())
                .build();
    }

}
