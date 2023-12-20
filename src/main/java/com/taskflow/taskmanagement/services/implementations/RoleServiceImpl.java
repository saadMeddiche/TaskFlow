package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.entities.Role;
import com.taskflow.taskmanagement.repositories.RoleRepository;
import com.taskflow.taskmanagement.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
