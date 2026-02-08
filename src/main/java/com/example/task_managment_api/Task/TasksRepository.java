package com.example.task_managment_api.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {}
