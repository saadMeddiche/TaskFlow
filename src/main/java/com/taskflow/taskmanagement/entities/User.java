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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User implements UserDetails {

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

    @Embedded
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnoreProperties("users")
    private List<Role> roles;

    @OneToMany
    private List<Card> cards;


    public User(String username, String email, String firstName, String middleName , String lastName,  String password) {
        this.username = username;
        this.email = new AddressEmail(email);
        this.name = new FullName(firstName, middleName, lastName);
        this.password = password;
    }

    public User(Long id, String username, String email, String firstName, String middleName , String lastName,  String password , List<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = new AddressEmail(email);
        this.name = new FullName(firstName, middleName, lastName);
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}