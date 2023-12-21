package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.repositories.UserRepository;
import com.taskflow.taskmanagement.services.UserService;
import com.taskflow.taskmanagement.services.validations.UserValdiationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserValdiationService validation;


    public User createUser(User user){

        validation.validateUserOnCreating(user);

        return userRepository.save(user);
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return findByUsername(username);
            }
        };
    }




}
