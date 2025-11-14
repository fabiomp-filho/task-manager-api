package br.fabiomartins.taskmanager.task.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequestDTO {
    @NotBlank(message = "The title is required")
    @Size(min = 3, max = 250, message = "The title must be between 3 and 250 characters long.")
    private String title;

    private String description;

    private Boolean done;
}
