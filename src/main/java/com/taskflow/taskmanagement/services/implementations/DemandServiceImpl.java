package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.entities.DemandReplacement;
import com.taskflow.taskmanagement.repositories.DemandRepository;
import com.taskflow.taskmanagement.services.DemandService;
import com.taskflow.taskmanagement.services.validations.DemandValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemandServiceImpl implements DemandService {

    private final DemandRepository demandRepository;

    private final DemandValidationService validation;
    @Override
    public DemandReplacement createDemand(DemandReplacement demand) {
        validation.validateDemandOnCreating(demand);
        return demandRepository.save(demand);
    }

    @Override
    public Boolean isTaskAReplaced(Long taskId) {
        return demandRepository.isTaskAReplaced(taskId);
    }
}
