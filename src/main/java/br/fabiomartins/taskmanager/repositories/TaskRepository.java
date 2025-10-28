package br.fabiomartins.taskmanager.repositories;

import br.fabiomartins.taskmanager.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
