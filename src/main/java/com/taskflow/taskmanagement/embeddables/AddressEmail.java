package com.taskflow.taskmanagement.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@ToString
public class AddressEmail {

    @NotNull(message = "The address email can not be null")
    @NotBlank(message = "The address email can not be blank")
    @Email(message = "Invalid email format")
    @Column(nullable = false)
    private String addressEmail;

}