package com.taskflow.taskmanagement.repositories;

import com.taskflow.taskmanagement.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
