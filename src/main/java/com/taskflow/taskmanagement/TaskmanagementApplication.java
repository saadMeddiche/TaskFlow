package com.taskflow.taskmanagement;

import com.taskflow.taskmanagement.entities.Permission;
import com.taskflow.taskmanagement.entities.Role;
import com.taskflow.taskmanagement.entities.Tag;
import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.permissions.TagPermissions;
import com.taskflow.taskmanagement.permissions.TaskPermissions;
import com.taskflow.taskmanagement.repositories.TagRepository;
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

	private final UserRepository userRepository;
	private final TagRepository tagRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

	@Bean
	public ApplicationRunner createDefaultData(){
		return new ApplicationRunner() {

			@Override
			public void run(ApplicationArguments args) {
				createDefaultDataForOfUsers();
				createDefaultTags();
			}
		};
	}


	private void createDefaultDataForOfUsers(){

		Permission accessToAll = new Permission(1L , "*");

		List<Permission> permissions = new ArrayList<>();
		permissions.add(new Permission(2L , "DELETE_OWN_CARD"));
		permissions.add(new Permission(3L , "EDIT_OWN_CARD"));
		permissions.add(new Permission(4L , "ADD_OWN_CARD"));
		permissions.add(new Permission(5L , "BIND_TAGS_TO_OWN_CARD"));
		permissions.add(new Permission(6L , TagPermissions.ADD_TAG.name()));
		permissions.add(new Permission(7L , TagPermissions.DELETE_TAG.name()));
		permissions.add(new Permission(8L , TaskPermissions.CREATE_TASK.name()));
		permissions.add(new Permission(9L , TaskPermissions.ASSIGN_TASK.name()));
		permissions.add(new Permission(10L , TaskPermissions.MARK_TASK_AS_DONE.name()));
		permissions.add(new Permission(11L , TaskPermissions.REPLACE_TASK.name()));
		permissions.add(new Permission(12L , TaskPermissions.DEMAND_REPLACEMENT.name()));
		permissions.add(new Permission(13L , TaskPermissions.DELETE_TASK.name()));


		Role adminRole = new Role(1L , "LEAD_ADMIN" , List.of(accessToAll));

		Role memberRole = new Role(2L , "MEMBER" , permissions);

		List<User> users = new ArrayList<>();
		users.add(new User(1L,"Admin" , "admin@gmail.com" , "Saad" , null , "Meddiche" ,"Password#0000" , List.of(adminRole)));
		users.add(new User(2L ,"User1", "user1@gmail.com", "John", "Smith", "Doe", "Password#0001" , List.of(memberRole)));
		users.add(new User(3L ,"User2", "user2@gmail.com", "Jane", "Miller", "Smith", "Password#0002" , List.of(memberRole)) );
		users.add(new User(4L ,"User3", "user3@gmail.com", "Alice", null, "Johnson", "Password#0003" , List.of(memberRole)));
		users.add(new User(5L ,"User4", "user4@gmail.com", "Bob", null, "Miller", "Password#0004" , List.of(memberRole)));
		users.add(new User(6L,"User5", "user5@gmail.com", "Eva", null, "Davis", "Password#0005" , List.of()));

		userRepository.saveAll(users);

	}

	private void createDefaultTags(){
		List<Tag> tags = new ArrayList<>();
		tags.add(Tag.builder().id(1L).name("Backend").tasks(List.of()).build());
		tags.add(Tag.builder().id(2L).name("Frontend").tasks(List.of()).build());
		tags.add(Tag.builder().id(3L).name("Mobile").tasks(List.of()).build());
		tags.add(Tag.builder().id(4L).name("Database").tasks(List.of()).build());
		tags.add(Tag.builder().id(5L).name("DevOps").tasks(List.of()).build());
		tags.add(Tag.builder().id(6L).name("Security").tasks(List.of()).build());
		tags.add(Tag.builder().id(7L).name("Testing").tasks(List.of()).build());
		tags.add(Tag.builder().id(8L).name("Design").tasks(List.of()).build());

		tagRepository.saveAll(tags);

	}



}
