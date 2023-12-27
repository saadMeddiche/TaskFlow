package com.taskflow.taskmanagement.repositories;

import com.taskflow.taskmanagement.entities.DemandReplacement;
import org.springframework.data.jpa.repository.Query;

public interface DemandRepository extends BaseRepository<DemandReplacement> {

    @Query("SELECT count(d) FROM DemandReplacement d WHERE d.newTask.id = :taskId  AND d.status = 'ACCEPTED'")
    public Boolean isTaskAReplaced(Long taskId);
}
