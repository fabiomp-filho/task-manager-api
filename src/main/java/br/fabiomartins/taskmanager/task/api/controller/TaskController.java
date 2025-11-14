package br.fabiomartins.taskmanager.task.api.controller;

import br.fabiomartins.taskmanager.task.api.dto.TaskRequestDTO;
import br.fabiomartins.taskmanager.task.api.dto.TaskResponseDTO;
import br.fabiomartins.taskmanager.task.domain.model.Task;
import br.fabiomartins.taskmanager.task.domain.port.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> list() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody TaskRequestDTO request) {
        TaskResponseDTO created = taskService.create(request);

        return ResponseEntity.created(URI.create("/api/v1/tasks/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid TaskRequestDTO request) {
        return ResponseEntity.ok(taskService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        taskService.delete(id);
        return ResponseEntity.ok("Task deleted sucessfully");
    }

    private Task toDomain(TaskRequestDTO dto, UUID boardId) {
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .done(Boolean.TRUE.equals(dto.getDone()))
                .boardId(boardId)
                .build();
    }

    private TaskResponseDTO toResponse(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isDone(),
                LocalDateTime.ofInstant(task.getCreatedAt(), ZoneOffset.UTC)
        );
    }
}
