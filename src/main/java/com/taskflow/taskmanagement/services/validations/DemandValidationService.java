package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.DemandReplacement;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.ValidationException;
import com.taskflow.taskmanagement.services.AuthenticationService;
import com.taskflow.taskmanagement.services.CardService;
import com.taskflow.taskmanagement.services.DemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class DemandValidationService extends BaseValidation {

    private CardService cardService;

    private AuthenticationService auth;

    private DemandService demandService;

    public DemandValidationService(CardService cardService , AuthenticationService auth) {
        this.cardService = cardService;
        this.auth = auth;
    }

    public void validateDemandOnCreating(DemandReplacement demand) {

        throwExceptionIf(CURRENT_TASK_IS_SAME_AS_NEW_TASK, demand , ValidationException::new , "The Current Task Is The Same As The New Task");

        throwExceptionIf(AUTHENTICATE_USER_HAVE_ENOUGH_PERMISSIONS, null , ValidationException::new , "You Don't Have Enough Modification Cards");


    }

    private static final Predicate<DemandReplacement> CURRENT_TASK_IS_SAME_AS_NEW_TASK = demand -> demand.getCurrentTask().getId().equals(demand.getNewTask().getId());

    private final Predicate<Void> AUTHENTICATE_USER_HAVE_ENOUGH_PERMISSIONS = (Void) -> cardService.userCanUseModifyCard(auth.getCurrentAuthenticatedUser());


}
