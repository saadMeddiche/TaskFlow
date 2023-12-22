package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class TaskValidationService extends BaseValidation {

    private static final Predicate<Task> TASK_HAS_LESS_THAN_THREE_TAGS = task -> task.getTags().size() < 3;

    public void validateTaskOnCreating(Task task) {

        validateObject(task);

        throwExceptionIf(TASK_HAS_LESS_THAN_THREE_TAGS, task, ValidationException::new, "Task must have at least 3 tags");

    }

}
