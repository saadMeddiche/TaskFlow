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
    private String hashedPassword;


    public Password(String nonHashedPassword) {
        this.hashedPassword = hashPassword(nonHashedPassword);
    }

    public boolean isHashedPasswordEqualsNonHashedPassword(String nonHashedPassword) {
         return  new BCryptPasswordEncoder().matches(hashedPassword, nonHashedPassword);
    }

     public String hashPassword(String nonHashedPassword) {
         return new BCryptPasswordEncoder().encode(nonHashedPassword);

     }
}