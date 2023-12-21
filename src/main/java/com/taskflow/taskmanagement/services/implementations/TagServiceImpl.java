package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.entities.Tag;
import com.taskflow.taskmanagement.repositories.TagRepository;
import com.taskflow.taskmanagement.services.TagService;
import com.taskflow.taskmanagement.services.validations.TagValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class TagServiceImpl extends BaseService<Tag ,Long> implements TagService  {

    private TagRepository tagRepository;

    private TagValidationService validation;

    public TagServiceImpl(TagRepository tagRepository) {
        super(tagRepository , Tag.class);
        this.tagRepository = tagRepository;
    }


    @Override
    public Tag createTag(Tag tag) {
        validation.validateTagOnCreating(tag);
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long tagId) {
        deleteEntityById(tagId);
    }
}
