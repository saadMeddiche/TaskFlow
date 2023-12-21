package com.taskflow.taskmanagement.dtos.authentication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    @NotNull(message = "The username of role can not be null")
    @NotBlank(message = "The username of role can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in username")
    private String username;

    @NotNull(message = "The password can not be null")
    @NotBlank(message = "The password can not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character (@#$%^&+=)")
    private String password;
}
