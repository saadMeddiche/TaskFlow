package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.DateValidationException;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.ValidationException;
import com.taskflow.taskmanagement.repositories.DemandRepository;
import com.taskflow.taskmanagement.services.AuthenticationService;
import com.taskflow.taskmanagement.services.CardService;
import com.taskflow.taskmanagement.services.DemandService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Predicate;

@Component
public class TaskValidationService extends BaseValidation {

    private AuthenticationService auth;
    private CardService cardService;

    private DemandService demandService;

    public TaskValidationService(AuthenticationService auth , CardService cardService , DemandService demandService) {
        this.auth = auth;
        this.cardService = cardService;
        this.demandService = demandService;
    }

    public void validateTaskOnCreating(Task task) {

        validateObject(task);

        throwExceptionIf(TASK_HAS_LESS_THAN_THREE_TAGS, task, ValidationException::new, "Task must have at least 3 tags");

        throwExceptionIf(DATE_STARTS_AFTER_ENDS, task, DateValidationException::new, "Start date must be before end date");

    }

    public void validateTaskOnAssigningAdditionalTask(Task task) {

        throwExceptionIf(TASK_ASSIGNER_NOT_SAME_AS_ASSIGNED_USER, task, ValidationException::new, "Task assigner must be same as assigned user");

        throwExceptionIf(TASK_CREATOR_NOT_THE_ASSIGNER , task, ValidationException::new, "Task creator must be same as assigner");
    }

    public void validateTaskOnAssignTask(Task task) {
        // No Validation required
    }

    public void validateTaskOnDeleting(Task task) {

        throwExceptionIf(TASK_IS_ALREADY_REPLACED_ONCE , task, ValidationException::new, "Task is already replaced once");

        throwExceptionIf(AUTHENTICATED_USER_IS_NOT_THE_ASSIGNEE_OR_CREATOR, task, ValidationException::new, "You must be the assignee or creator of the task");

        throwExceptionIf(AUTHENTICATED_USER_CAN_NOT_USE_DELETE_CARD , null , ValidationException::new, "You can not use delete card. You have used all your cards");
    }

    public void validateTaskOnMarkingAsDone(Task task) {
        validateTaskOnMarkingStatus(task);
    }

    public void validateTaskOnMarkingAsInProgress(Task task) {
        validateTaskOnMarkingStatus(task);
    }

    private void validateTaskOnMarkingStatus(Task task) {

        throwExceptionIf(TASK_END_DATE_HAS_PASSED, task, DateValidationException::new, "Task has passed its end date");

        throwExceptionIf(AUTHENTICATED_USER_IS_NOT_THE_ASSIGNEE_OR_CREATOR, task, ValidationException::new, "You must be the assignee or creator of the task");

    }

    private static final Predicate<Task> TASK_HAS_LESS_THAN_THREE_TAGS = task -> task.getTags().size() < 3;

    private static final Predicate<Task> DATE_STARTS_AFTER_ENDS = task -> task.getStartDate().isAfter(task.getEndDate());

    private static final Predicate<Task> TASK_ASSIGNER_NOT_SAME_AS_ASSIGNED_USER = task -> !task.getAssignedBy().equals(task.getAssignedTo());

    private static final Predicate<Task> TASK_CREATOR_NOT_THE_ASSIGNER = task -> ! task.getCreatedBy().equals(task.getAssignedBy());

    private static final Predicate<Task> TASK_END_DATE_HAS_PASSED = task -> task.getEndDate().isBefore(LocalDate.now());

    private final Predicate<Task> TASK_IS_ALREADY_REPLACED_ONCE = (task) -> demandService.isTaskAReplaced(task.getId());

    private final Predicate<Task> TASK_CREATOR_NOT_THE_AUTHENTICATED_USER = task -> !task.getCreatedBy().equals(auth.getCurrentAuthenticatedUser());

    private final Predicate<Task> TASK_ASSIGNEE_NOT_THE_AUTHENTICATED_USER = task -> !task.getAssignedTo().equals(auth.getCurrentAuthenticatedUser());

    private final Predicate<Task> AUTHENTICATED_USER_IS_NOT_THE_ASSIGNEE_OR_CREATOR = task -> TASK_ASSIGNEE_NOT_THE_AUTHENTICATED_USER.test(task) || TASK_CREATOR_NOT_THE_AUTHENTICATED_USER.test(task);

    private final Predicate<Void> AUTHENTICATED_USER_CAN_NOT_USE_DELETE_CARD = (Void) -> !cardService.userCanUseDeleteCard(auth.getCurrentAuthenticatedUser());

}
