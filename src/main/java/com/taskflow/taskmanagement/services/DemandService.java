package com.taskflow.taskmanagement.services;

import com.taskflow.taskmanagement.entities.DemandReplacement;

public interface DemandService {

    public DemandReplacement createDemand(DemandReplacement demand);
    public Boolean isTaskAReplaced(Long taskId);
}
