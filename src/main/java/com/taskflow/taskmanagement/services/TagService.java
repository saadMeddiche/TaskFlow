package com.taskflow.taskmanagement.services;

import com.taskflow.taskmanagement.entities.Tag;

public interface TagService {

    public Tag createTag(Tag tag);

    public void deleteTag(Long tagId);
}
