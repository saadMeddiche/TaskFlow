package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.taskflow.taskmanagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class UserValdiationService extends BaseValidation {

    private  UserRepository userRepository;

    private final Predicate<User> EMAIL_ALREADY_EXISTS = user -> userRepository.existsByEmail(user.getEmail());

    private final Predicate<User> USERNAME_ALREADY_EXISTS = user -> userRepository.existsByUsername(user.getUsername());

    public void validateUserOnCreating(User user) {

        validateObject(user);

        throwExceptionIf(EMAIL_ALREADY_EXISTS, user, AlreadyExistsException::new, "Email Already Exists");

        throwExceptionIf(USERNAME_ALREADY_EXISTS, user, AlreadyExistsException::new, "Username Already Exists");

    }


}
