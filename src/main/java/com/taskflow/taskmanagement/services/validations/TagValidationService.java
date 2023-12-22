package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.Tag;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.taskflow.taskmanagement.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class TagValidationService extends BaseValidation {

    private TagRepository tagRepository;

    private final Predicate<Tag> TAG_NAME_ALREADY_EXISTS = tag -> tagRepository.existsByName(tag.getName());

    public void validateTagOnCreating(Tag tag) {

        validateObject(tag);

        throwExceptionIf(TAG_NAME_ALREADY_EXISTS, tag, AlreadyExistsException::new, "Tag Name Already Exists");
    }


}
