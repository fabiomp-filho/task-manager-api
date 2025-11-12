package br.fabiomartins.taskmanager.task.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringTaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findByBoardId(UUID boardId);
}
