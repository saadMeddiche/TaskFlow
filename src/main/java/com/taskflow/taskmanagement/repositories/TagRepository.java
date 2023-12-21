package com.taskflow.taskmanagement.repositories;

import com.taskflow.taskmanagement.entities.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends BaseRepository<Tag, Long> {

    Boolean existsByName(String name);
}
