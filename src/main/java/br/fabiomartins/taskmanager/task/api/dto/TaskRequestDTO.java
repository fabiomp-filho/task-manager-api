package br.fabiomartins.taskmanager.task.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequestDTO {
    @NotBlank(message = "The title is required")
    @Size(min = 3, max = 100, message = "The title must be between 3 and 100 characters long.")
    private String title;

    @Size(max = 500, message = "The description must have a maximum of 500 characters")
    private String description;

    private Boolean isCompleted;
}
