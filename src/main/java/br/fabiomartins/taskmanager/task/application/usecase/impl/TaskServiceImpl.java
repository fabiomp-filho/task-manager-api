package br.fabiomartins.taskmanager.task.application.usecase.impl;

import br.fabiomartins.taskmanager.task.domain.model.Task;
import br.fabiomartins.taskmanager.task.domain.port.TaskRepositoryPort;
import br.fabiomartins.taskmanager.task.domain.port.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepositoryPort repo;

    @Override
    public Task create(Task t) {
        t.setId(UUID.randomUUID());

        return repo.save(t);
    }

    @Override
    public Task update(UUID id, Task t) {
        Task existing = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        existing.setTitle(t.getTitle());
        existing.setDescription(t.getDescription());
        existing.setDueDate(t.getDueDate());
        existing.setDone(t.isDone());
        return repo.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public Task getById(UUID id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> listByBoard(UUID boardId) {
        return repo.findByBoardId(boardId);
    }

    @Override
    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Task not found");
        repo.deleteById(id);
    }
}
