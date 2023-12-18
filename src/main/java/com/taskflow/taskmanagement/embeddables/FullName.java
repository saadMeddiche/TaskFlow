package com.taskflow.taskmanagement.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class FullName {

    @NotNull(message = "The first name can not be null")
    @NotBlank(message = "The first name can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in first name")
    private String first;

    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed in middle name")
    private String middle;

    @NotNull(message = "The last name can not be null")
    @NotBlank(message = "The last name can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed in last name")
    private String last;
}