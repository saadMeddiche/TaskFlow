package com.taskflow.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<O,ID> extends JpaRepository<O,ID> {

}
