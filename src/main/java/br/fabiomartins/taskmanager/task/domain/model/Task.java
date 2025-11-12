package br.fabiomartins.taskmanager.task.domain.model;

import br.fabiomartins.taskmanager.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Task {
    private UUID id;
    private String title;
    private String description;
    private boolean done;
    private Instant dueDate;
    private UUID boardId;
}
