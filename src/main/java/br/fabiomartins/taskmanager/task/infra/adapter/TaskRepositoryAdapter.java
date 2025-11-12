package br.fabiomartins.taskmanager.task.infra.adapter;

import br.fabiomartins.taskmanager.task.domain.model.Task;
import br.fabiomartins.taskmanager.task.domain.port.TaskRepositoryPort;
import br.fabiomartins.taskmanager.task.infra.jpa.SpringTaskRepository;
import br.fabiomartins.taskmanager.task.infra.jpa.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final SpringTaskRepository repository;

    private Task toDomain(TaskEntity e) {
        return Task.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .done(e.isDone())
                .dueDate(e.getDueDate())
                .boardId(e.getBoardId())
                .build();
    }

    private TaskEntity toEntity(Task t) {
        TaskEntity entity = TaskEntity.builder()
                .title(t.getTitle())
                .description(t.getDescription())
                .done(t.isDone())
                .dueDate(t.getDueDate())
                .boardId(t.getBoardId())
                .build();

        if (t.getId() != null) {
            entity.setId(t.getId());
        }

        return entity;
    }

    @Override
    public Task save(Task task) {
        TaskEntity e = toEntity(task);
        e = repository.save(e);
        return toDomain(e);
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Task> findByBoardId(UUID boardId) {
        return repository.findByBoardId(boardId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
