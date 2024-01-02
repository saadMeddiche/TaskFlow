package com.taskflow.taskmanagement.services;

import com.taskflow.taskmanagement.entities.Card;
import com.taskflow.taskmanagement.entities.User;

public interface CardService {

    public void createDefaultCards(User user);
    public void createDefaultModificationCardForUser(User user);
    public void createDefaultDeletionCardForUser(User user);
    public void useModifyCard(User user);
    public void useDeleteCard(User user);

    public boolean userCanUseModifyCard(User user);

    public boolean userCanUseDeleteCard(User user);
}
