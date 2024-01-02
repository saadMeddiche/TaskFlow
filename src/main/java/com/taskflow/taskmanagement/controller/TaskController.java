package com.taskflow.taskmanagement.controller;

import com.taskflow.taskmanagement.converters.TaskConverter;
import com.taskflow.taskmanagement.dtos.task.request.TaskAssignRequest;
import com.taskflow.taskmanagement.dtos.task.request.TaskRequest;
import com.taskflow.taskmanagement.dtos.task.response.TaskResponse;
import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskConverter taskConverter;


    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequest taskRequest) {

        Task task = taskConverter.convertToEntity(taskRequest);

        Task createdTask = taskService.createTask(task);

        TaskResponse createdTaskResponse = taskConverter.convertToTaskResponse(createdTask);

        return new ResponseEntity<>(createdTaskResponse , HttpStatus.CREATED);
    }

    @PostMapping("/assignTask")
    public ResponseEntity<?> assignTask(@Valid @RequestBody TaskAssignRequest taskRequest) {

        Task task = taskConverter.convertToEntity(taskRequest);

        taskService.assignTask(task);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping({"/assignAdditionalTask/{taskId}" , "/assignAdditionalTask/"} )
    public ResponseEntity<?> assignAdditionalTask(@PathVariable Long taskId) {

        Task task = taskConverter.convertToEntity(taskId);

        taskService.assignAdditionalTask(task);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/setTaskAsDone/{taskId}")
    public ResponseEntity<?> setTaskAsDone(@PathVariable Long taskId) {

        Task task = taskConverter.convertToEntity(taskId);

        taskService.markTaskAsDone(task);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/setTaskAsInProgress/{taskId}")
    public ResponseEntity<?> setTaskAsInProgress(@PathVariable Long taskId) {

        Task task = taskConverter.convertToEntity(taskId);

        taskService.markTaskAsInProgress(task);

        return new ResponseEntity<>(HttpStatus.OK);

    }
    @DeleteMapping({"/useDeleteTask/{taskId}" , "/useDeleteTask/"} )
    public ResponseEntity<?> useDeleteTask(@PathVariable Long taskId) {

        Task task = taskConverter.convertToEntity(taskId);

        taskService.deleteTask(task);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/filterTasksByYear/{year}")
    public ResponseEntity<?> filterTasksByYear(@PathVariable int year) {
        List<Task> tasks = taskService.filterTasksByYear(year);
        return new ResponseEntity<>(tasks , HttpStatus.OK);
    }

    @GetMapping("/filterTasksByMonth/{month}")
    public ResponseEntity<?> filterTasksByMonth(@PathVariable int month) {
        List<Task> tasks = taskService.filterTasksByMonth(month);
        return new ResponseEntity<>(tasks , HttpStatus.OK);
    }
}
