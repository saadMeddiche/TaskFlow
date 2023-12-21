package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.Tag;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.taskflow.taskmanagement.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagValidationService extends BaseValidation {

    private final TagRepository tagRepository;

    public void validateTagOnCreating(Tag tag) {

        validateObject(tag);

        throwExceptionIfTagAlreadyExists(tag);
    }

    private void throwExceptionIfTagAlreadyExists(Tag tag) {
        if(tagRepository.existsByName(tag.getName())) {
            throw new AlreadyExistsException("Tag already exists");
        }
    }
}
