package com.taskflow.taskmanagement.services;

import com.taskflow.taskmanagement.entities.Card;
import com.taskflow.taskmanagement.entities.User;

public interface CardService {

    public void createDefaultModificationCardForUser(User user);
    public void createDefaultDeletionCardForUser(User user);
    public void useModifyCard(User user);
    public void useDeleteCard(User user);

    public boolean checkIfUserCanUseModifyCard(User user);

    public boolean checkIfUserCanUseDeleteCard(User user);
}
