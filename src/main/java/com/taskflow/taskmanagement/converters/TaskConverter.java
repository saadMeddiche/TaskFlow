package com.taskflow.taskmanagement.converters;

import com.taskflow.taskmanagement.dtos.task.request.TaskRequest;
import com.taskflow.taskmanagement.dtos.task.response.TaskResponse;
import com.taskflow.taskmanagement.entities.Tag;
import com.taskflow.taskmanagement.entities.Task;
import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.enums.TaskStatus;
import com.taskflow.taskmanagement.repositories.TaskRepository;
import com.taskflow.taskmanagement.services.AuthenticationService;
import com.taskflow.taskmanagement.services.TagService;
import com.taskflow.taskmanagement.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskConverter {

    private final TagService tagService;

    private final AuthenticationService authenticationService;

    private final UserConverter userConverter;

    public Task convertToEntity(TaskRequest taskRequest) {

        List<Tag> tags = mapTags(taskRequest.getTagsId());

        User user = authenticationService.getCurrentAuthenticatedUser();

        return Task.builder()
                .id(null)
                .name(taskRequest.getName())
                .description(taskRequest.getDescription())
                .createdBy(user)
                .startDate(taskRequest.getStartDate())
                .endDate(taskRequest.getEndDate())
                .status(TaskStatus.TODO)
                .tags(tags)
                .build();
    }

    public TaskResponse convertToTaskResponse(Task task) {

        return TaskResponse.builder()
                .name(task.getName())
                .description(task.getDescription())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .createdBy(userConverter.convertToUserResponse(task.getCreatedBy()))
                .assignedBy(userConverter.convertToUserResponse(task.getAssignedBy()))
                .assignedTo(userConverter.convertToUserResponse(task.getAssignedTo()))
                .status(task.getStatus())
                .tags(task.getTags())
                .build();
    }

    private List<Tag> mapTags(List<Long> tagsId) {
        return tagsId.stream().map(tagService::getTagById).toList();
    }
}
