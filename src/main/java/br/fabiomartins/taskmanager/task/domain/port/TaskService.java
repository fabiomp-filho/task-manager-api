package br.fabiomartins.taskmanager.task.domain.port;

import br.fabiomartins.taskmanager.task.domain.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    Task create(Task t);
    Task update(UUID id, Task t);
    Task getById(UUID id);
    List<Task> listByBoard(UUID boardId);
    void delete(UUID id);

}
