package com.taskflow.taskmanagement.mappers;

import com.taskflow.taskmanagement.dtos.task.request.TaskRequest;
import com.taskflow.taskmanagement.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task convertToEntity(TaskRequest taskRequest);

    TaskRequest convertToDto(Task task);

    List<TaskRequest> convertToDto(List<Task> tasks);
}
