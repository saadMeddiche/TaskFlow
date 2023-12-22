package com.taskflow.taskmanagement.controller;

import com.taskflow.taskmanagement.dtos.task.request.TaskRequest;
import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.mappers.TaskMapper;
import com.taskflow.taskmanagement.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequest taskRequest) {

        Task task = TaskMapper.INSTANCE.convertToEntity(taskRequest);

        Task createdTask = taskService.createTask(task);

        return new ResponseEntity<>( createdTask, HttpStatus.CREATED);
    }
}
