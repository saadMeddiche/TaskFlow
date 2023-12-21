package com.taskflow.taskmanagement.controller;

import com.taskflow.taskmanagement.converters.TagConverter;
import com.taskflow.taskmanagement.dtos.tag.request.TagRequest;
import com.taskflow.taskmanagement.services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<?> createTag(@Valid @RequestBody TagRequest tagRequest) {
        tagService.createTag(TagConverter.convertToEntity(tagRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
