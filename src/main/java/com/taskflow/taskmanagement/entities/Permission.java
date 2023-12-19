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
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull(message = "The name of permission can not be null")
    @NotBlank(message = "The name of permission can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in name of permission")
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnoreProperties("permissions")
    private List<Role> roles;

    public Permission(Long id , String name) {
        this.id = id;
        this.name = name;
    }

}