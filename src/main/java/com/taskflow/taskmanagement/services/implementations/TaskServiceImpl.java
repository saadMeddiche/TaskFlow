package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.repositories.TaskRepository;
import com.taskflow.taskmanagement.services.TaskService;
import com.taskflow.taskmanagement.services.validations.TaskValidationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskValidationService validation;
    @Override
    public Task createTask(@Valid Task task) {
        validation.validateTaskOnCreating(task);
        return taskRepository.save(task);
    }

    @Override
    public void assignTask(Task task) {
        validation.validateTaskOnAssignTask(task);
        taskRepository.save(task);
    }

    @Override
    public void assignAdditionalTask(Task task) {
        validation.validateTaskOnAssigningAdditionalTask(task);
        taskRepository.save(task);
    }

    @Override
    public void markTaskAsDone(Task task) {

    }

    @Override
    public void replaceTask(Task currentTask, Task newTask) {

    }

    @Override
    public void demandReplacement(Task task) {

    }

    @Override
    public void deleteTask(Task task) {

    }
}
