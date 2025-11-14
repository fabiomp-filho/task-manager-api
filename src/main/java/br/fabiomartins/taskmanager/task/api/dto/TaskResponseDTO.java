package br.fabiomartins.taskmanager.task.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TaskResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private Boolean done;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createdAt;
}
