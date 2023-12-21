package com.taskflow.taskmanagement.repositories;

import com.taskflow.taskmanagement.entities.Tag;

public interface TagRepository extends BaseRepository<Tag, Long> {

    Boolean existsByName(String name);
}
