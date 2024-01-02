package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.enums.TaskStatus;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.DoNotExistException;
import com.taskflow.taskmanagement.repositories.TaskRepository;
import com.taskflow.taskmanagement.services.AuthenticationService;
import com.taskflow.taskmanagement.services.CardService;
import com.taskflow.taskmanagement.services.TaskService;
import com.taskflow.taskmanagement.services.validations.TaskValidationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskValidationService validation;

    private final CardService cardService;

    private final AuthenticationService auth;
    @Override
    public Task createTask(@Valid Task task) {
        validation.validateTaskOnCreating(task);
        return taskRepository.save(task);
    }

    @Override
    public Task getById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new DoNotExistException("Task not found"));
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

        validation.validateTaskOnMarkingAsDone(task);

        task.setStatus(TaskStatus.Done);

        taskRepository.save(task);

    }

    @Override
    public void markTaskAsInProgress(Task task) {

        validation.validateTaskOnMarkingAsInProgress(task);

        task.setStatus(TaskStatus.InProgress);

        taskRepository.save(task);
    }

    @Override
    public void replaceTask(Task currentTask, Task newTask) {

    }

    @Override
    public void demandReplacement(Task task) {
    }

    @Override
    public void deleteTask(Task task) {


        validation.validateTaskOnDeleting(task);

        User authnticatedUser = auth.getCurrentAuthenticatedUser();

        // if the authenticated user is the creator , do not use cards (jetons)
        if(!authnticatedUser.equals(task.getCreatedBy())) cardService.useDeleteCard(auth.getCurrentAuthenticatedUser());

        taskRepository.deleteById(task.getId());

    }

    @Override
    public List<Task> filterTasksByYear(int year) {
        return taskRepository.findAll().stream().filter(task -> task.getStartDate().getYear() == year).toList();
    }

    @Override
    public List<Task> filterTasksByMonth(int year , int month) {
        return taskRepository.findAll().stream().filter(task -> task.getStartDate().getYear() == year && task.getStartDate().getMonthValue() == month).toList();
    }
}
