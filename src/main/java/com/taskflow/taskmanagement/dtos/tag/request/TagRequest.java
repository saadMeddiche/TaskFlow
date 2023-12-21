package com.taskflow.taskmanagement.dtos.tag.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    @NotNull(message = "The name of role can not be null")
    @NotBlank(message = "The name of role can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in name")
    private String name;
}
