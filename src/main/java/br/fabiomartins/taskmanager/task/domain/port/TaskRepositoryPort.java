package br.fabiomartins.taskmanager.task.domain.port;

import br.fabiomartins.taskmanager.task.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(UUID id);
    List<Task> findByBoardId(UUID boardId);
    void deleteById(UUID id);
    boolean existsById(UUID id);
}
