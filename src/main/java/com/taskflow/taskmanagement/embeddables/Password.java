package com.taskflow.taskmanagement.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Password {


    @NotNull(message = "The password can not be null")
    @NotBlank(message = "The password can not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character (@#$%^&+=)")
    private String hashedPassword;

    public Password(String non_hashedPassword) {
         this.hashedPassword = hashPassword(non_hashedPassword);
    }

    public boolean isHashedPasswordEqualsNonHashedPassword(String nonHashedPassword) {
         return  new BCryptPasswordEncoder().matches(hashedPassword, nonHashedPassword);
    }

     public String hashPassword(String nonHashedPassword) {
         return new BCryptPasswordEncoder().encode(nonHashedPassword);

     }
}