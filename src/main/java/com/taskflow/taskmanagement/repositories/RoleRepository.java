package com.taskflow.taskmanagement.repositories;

import com.taskflow.taskmanagement.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
