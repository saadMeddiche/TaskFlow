package com.taskflow.taskmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taskflow.taskmanagement.embeddables.AddressEmail;
import com.taskflow.taskmanagement.embeddables.FullName;
import com.taskflow.taskmanagement.embeddables.Password;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull(message = "The username of role can not be null")
    @NotBlank(message = "The username of role can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in username")
    @Column(nullable = false)
    private String username;

    @Valid
    @Embedded
    private AddressEmail email;

    @Valid
    @Embedded
    private FullName name;

    @Valid
    @Embedded
    private Password password;

    @ManyToMany
    @JsonIgnoreProperties("users")
    private List<Role> roles;

    public User(String username, String email, String firstName, String middleName , String lastName,  String password) {
        this.username = username;
        this.email = new AddressEmail(email);
        this.name = new FullName(firstName, middleName, lastName);
        this.password = new Password(password);
    }

}