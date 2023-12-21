package com.taskflow.taskmanagement.services.validations;

import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.taskflow.taskmanagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValdiationService extends BaseValidation {

    private final UserRepository userRepository;

    public void validateUserOnCreating(User user) {

        validateObject(user);

        throwExceptionUserAlreadyExists(user);

    }

    private void throwExceptionUserAlreadyExists(User user) {

        if(emailAlreadyExists(user)) {
            throw new AlreadyExistsException("Email already exists");
        }

        if(usernameAlreadyExists(user)) {
            throw new AlreadyExistsException("Username already exists");
        }
    }

    private Boolean emailAlreadyExists(User user) {
        return userRepository.existsByEmail(user.getEmail().getAddressEmail());
    }

    private Boolean usernameAlreadyExists(User user) {
        return userRepository.existsByUsername(user.getUsername());
    }
}
