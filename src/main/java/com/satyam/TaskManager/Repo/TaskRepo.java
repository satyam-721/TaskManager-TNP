package com.satyam.TaskManager.Repo;

import com.satyam.TaskManager.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Long> {
    Object findByUsername(String username);
}
