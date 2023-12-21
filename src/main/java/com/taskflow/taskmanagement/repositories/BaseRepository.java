package com.taskflow.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface BaseRepository<O,ID> extends JpaRepository<O,ID> {

}
