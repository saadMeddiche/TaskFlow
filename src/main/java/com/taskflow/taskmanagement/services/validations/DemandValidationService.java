package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.DemandReplacement;
import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.ValidationException;
import com.taskflow.taskmanagement.services.AuthenticationService;
import com.taskflow.taskmanagement.services.CardService;
import com.taskflow.taskmanagement.services.DemandService;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class DemandValidationService extends BaseValidation {

    private CardService cardService;

    private AuthenticationService auth;

    private DemandService demandService;

    public DemandValidationService(CardService cardService , AuthenticationService auth , DemandService demandService) {
        this.cardService = cardService;
        this.auth = auth;
        this.demandService = demandService;
    }

    public void validateDemandOnCreating(DemandReplacement demand) {

        throwExceptionIf(CURRENT_TASK_IS_SAME_AS_NEW_TASK, demand , ValidationException::new , "The Current Task Is The Same As The New Task");

        throwExceptionIf(TASK_IS_REPLACED, demand.getCurrentTask() , ValidationException::new , "The Current Task Is Already Replaced");

        throwExceptionIf(TASK_IS_REPLACED, demand.getNewTask() , ValidationException::new , "The New Task Is Already Replaced");

        throwExceptionIf(AUTHENTICATE_USER_HAVE_ENOUGH_PERMISSIONS, null , ValidationException::new , "You Don't Have Enough Modification Cards");

    }

    private static final Predicate<DemandReplacement> CURRENT_TASK_IS_SAME_AS_NEW_TASK = demand -> demand.getCurrentTask().getId().equals(demand.getNewTask().getId());

    private final Predicate<Void> AUTHENTICATE_USER_HAVE_ENOUGH_PERMISSIONS = (Void) -> cardService.userCanUseModifyCard(auth.getCurrentAuthenticatedUser());

    private final Predicate<Task> TASK_IS_REPLACED = (task) -> demandService.isTaskAReplaced(task.getId());


}
