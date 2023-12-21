package com.taskflow.taskmanagement.dtos.authentication.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotNull(message = "The username of role can not be null")
    @NotBlank(message = "The username of role can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in username")
    private String username;

    @NotNull(message = "The first name can not be null")
    @NotBlank(message = "The first name can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in first name")
    @Column(nullable = false)
    private String firstName;

    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed in middle name")
    private String middleName;

    @NotNull(message = "The last name can not be null")
    @NotBlank(message = "The last name can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed in last name")
    private String lastName;

    @NotNull(message = "The address email can not be null")
    @NotBlank(message = "The address email can not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "The password can not be null")
    @NotBlank(message = "The password can not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character (@#$%^&+=)")
    private String password;
}
