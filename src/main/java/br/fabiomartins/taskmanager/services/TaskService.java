package br.fabiomartins.taskmanager.services;

import br.fabiomartins.taskmanager.exceptions.NotFoundException;
import br.fabiomartins.taskmanager.models.dtos.TaskRequest;
import br.fabiomartins.taskmanager.models.dtos.TaskResponse;
import br.fabiomartins.taskmanager.models.entities.Task;
import br.fabiomartins.taskmanager.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskResponse> findAll() {
        List<TaskResponse> tasks = taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

        log.info("Listing tasks. total found: {}", tasks.size());
        return tasks;
    }

    public Task findById(Long id) {
        log.info("Finding task by id: {}", id);
        return taskRepository.findById(id).orElseThrow(() -> {
            log.info("Task with id: {} not found", id);
            return new NotFoundException("Task not found");
        });
    }

    public TaskResponse create(TaskRequest request) {
        Task toSave = new Task();
        BeanUtils.copyProperties(request, toSave);
        log.info("Saving task: {}", toSave);
        return toResponse(taskRepository.save(toSave));
    }

    public TaskResponse update(Long id, TaskRequest request) {
        Task toUpdate = findById(id);
        BeanUtils.copyProperties(request, toUpdate, "id");
        log.info("Updating task: {}", toUpdate);
        return toResponse(taskRepository.save(toUpdate));
    }

    public void delete(Long id) {
        Task toDelete = findById(id);
        log.info("Deleting task: {}", toDelete);
        taskRepository.delete(toDelete);
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getIsCompleted(),
                task.getCreatedAt()
        );
    }
}
