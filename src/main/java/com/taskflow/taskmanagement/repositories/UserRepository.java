package com.taskflow.taskmanagement.repositories;

import com.taskflow.taskmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
