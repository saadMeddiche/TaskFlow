package com.taskflow.taskmanagement.repositories;

import com.taskflow.taskmanagement.entities.DemandReplacement;
import org.springframework.data.jpa.repository.Query;

public interface DemandRepository extends BaseRepository<DemandReplacement> {

    @Query("SELECT count(d) > 0 FROM DemandReplacement d WHERE d.task.id = :taskId  AND d.status = 'ACCEPTED'")
    Boolean isTaskAReplaced(Long taskId);

    @Query("SELECT count(d) > 0 FROM DemandReplacement d WHERE d.task.id = :taskId AND d.status = 'PENDING'")
    Boolean isThereAlreadyAPeddingDemand(Long taskId);
}
