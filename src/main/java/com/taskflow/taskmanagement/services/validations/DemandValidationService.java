package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.DemandReplacement;
import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.ValidationException;
import com.taskflow.taskmanagement.repositories.DemandRepository;
import com.taskflow.taskmanagement.services.AuthenticationService;
import com.taskflow.taskmanagement.services.CardService;
import com.taskflow.taskmanagement.services.DemandService;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class DemandValidationService extends BaseValidation {

    private CardService cardService;

    private AuthenticationService auth;

    private DemandRepository demandRepository;

    public DemandValidationService(CardService cardService , AuthenticationService auth , DemandRepository demandRepository) {
        this.cardService = cardService;
        this.auth = auth;
        this.demandRepository = demandRepository;
    }

    public void validateDemandOnCreating(DemandReplacement demand) {

        throwExceptionIf(AUTHENTICATED_USER_IS_NOT_ASSIGNED_TO_TASK, demand.getTask() , ValidationException::new , "The User Is Not Assigned To The Task");

        throwExceptionIf(TASK_IS_REPLACED, demand.getTask() , ValidationException::new , "The Task Is Already Replaced");

        throwExceptionIf(AUTHENTICATE_USER_HAVE_NOT_ENOUGH_MODIFICATION_CARDS, null , ValidationException::new , "You Don't Have Enough Modification Cards");

        throwExceptionIf(THERE_IS_ALREADY_A_PENDING_DEMAND, demand.getTask() , ValidationException::new , "There Is Already A Pending Demand");

    }

    private final Predicate<Void> AUTHENTICATE_USER_HAVE_NOT_ENOUGH_MODIFICATION_CARDS = (Void) -> !cardService.userCanUseModifyCard(auth.getCurrentAuthenticatedUser());

    private final Predicate<Task> TASK_IS_REPLACED = (task) -> demandRepository.isTaskAReplaced(task.getId());

    private final Predicate<Task> AUTHENTICATED_USER_IS_NOT_ASSIGNED_TO_TASK = (task) -> !task.getAssignedTo().getId().equals(auth.getCurrentAuthenticatedUser().getId());

    private final Predicate<Task> THERE_IS_ALREADY_A_PENDING_DEMAND = (task) -> demandRepository.isThereAlreadyAPeddingDemand(task.getId());


}
