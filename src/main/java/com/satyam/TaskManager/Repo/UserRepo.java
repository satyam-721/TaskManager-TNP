package com.satyam.TaskManager.Repo;

import com.satyam.TaskManager.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
