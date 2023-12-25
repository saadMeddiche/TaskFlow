package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.DateValidationException;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class TaskValidationService extends BaseValidation {

    private static final Predicate<Task> TASK_HAS_LESS_THAN_THREE_TAGS = task -> task.getTags().size() < 3;

    private static final Predicate<Task> DATE_STARTS_AFTER_ENDS = task -> task.getStartDate().isAfter(task.getEndDate());

    public void validateTaskOnCreating(Task task) {

        validateObject(task);

        throwExceptionIf(TASK_HAS_LESS_THAN_THREE_TAGS, task, ValidationException::new, "Task must have at least 3 tags");

        throwExceptionIf(DATE_STARTS_AFTER_ENDS, task, DateValidationException::new, "Start date must be before end date");

    }

    public void validateTaskOnAssigningAdditionalTask(Task task) {

    }

    public void validateTaskOnAssignTask(Task task) {

    }

}
