package com.taskflow.taskmanagement.entities;

import com.taskflow.taskmanagement.embeddables.AddressEmail;
import com.taskflow.taskmanagement.embeddables.FullName;
import com.taskflow.taskmanagement.embeddables.Password;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

    private String username;

    private AddressEmail email;

    private FullName name;

    private Password password;

}