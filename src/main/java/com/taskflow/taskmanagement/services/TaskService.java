package com.taskflow.taskmanagement.services;

import com.taskflow.taskmanagement.entities.Task;

public interface TaskService {

    public Task createTask(Task task);

    public void assignTask(Task task);

    public void markTaskAsDone(Task task);

    public void replaceTask(Task currentTask, Task newTask);

    public void demandReplacement(Task task);

    public void deleteTask(Task task);
}
