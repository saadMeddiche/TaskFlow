package com.taskflow.taskmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull(message = "The name of role can not be null")
    @NotBlank(message = "The name of role can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in name of role")
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @JsonIgnoreProperties("roles")
    private List<Permission> permissions;

    public Role(Long id , String name , List<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public Role(Long id , String name) {
        this.id = id;
        this.name = name;
    }
}