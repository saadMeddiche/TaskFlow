package com.taskflow.taskmanagement;

import com.taskflow.taskmanagement.entities.Permission;
import com.taskflow.taskmanagement.entities.Role;
import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.permissions.Tag;
import com.taskflow.taskmanagement.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class TaskmanagementApplication {

	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

	@Bean
	public ApplicationRunner createDefaultData(){
		return new ApplicationRunner() {

			@Override
			public void run(ApplicationArguments args) {
				createDefaultDataForOfUsers();
			}
		};
	}

	public void createDefaultDataForOfUsers(){

		Permission accessToAll = new Permission(1L , "*");

		List<Permission> permissions = new ArrayList<>();
		permissions.add(new Permission(2L , "DELETE_OWN_CARD"));
		permissions.add(new Permission(3L , "EDIT_OWN_CARD"));
		permissions.add(new Permission(4L , "ADD_OWN_CARD"));
		permissions.add(new Permission(5L , "BIND_TAGS_TO_OWN_CARD"));
		permissions.add(new Permission(6L , Tag.ADD_TAG.name()));
		permissions.add(new Permission(7L , Tag.DELETE_TAG.name()));

		Role adminRole = new Role(1L , "LEAD_ADMIN" , List.of(accessToAll));

		Role memberRole = new Role(2L , "MEMBER" , permissions);

		List<User> users = new ArrayList<>();
		users.add(new User(1L,"Admin" , "admin@gmail.com" , "Saad" , null , "Meddiche" ,"Password#Saad#0000" , List.of(adminRole)));
		users.add(new User(2L ,"User1", "user1@gmail.com", "John", "Smith", "Doe", "Password#John#0001" , List.of(memberRole)));
		users.add(new User(3L ,"User2", "user2@gmail.com", "Jane", "Miller", "Smith", "Password#Jane#0002" , List.of(memberRole)) );
		users.add(new User(4L ,"User3", "user3@gmail.com", "Alice", null, "Johnson", "Password#Alice#0003" , List.of(memberRole)));
		users.add(new User(5L ,"User4", "user4@gmail.com", "Bob", null, "Miller", "Password#Bob#0004" , List.of(memberRole)));
		users.add(new User(6L,"User5", "user5@gmail.com", "Eva", null, "Davis", "Password#Eva#0005" , List.of()));

		userRepository.saveAll(users);

	}



}
