package com.example.task_managment_api.Task;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/tasks")
public class TasksController {
    
    private final TasksService service;

    public TasksController(TasksService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tasks> getAllTasks() {
        return service.getAllTasks();
    }

    @PostMapping
    public String postMethodName(@RequestBody Tasks task) {
        return service.addTask(task);
    }

    @GetMapping("{id}")
    public List<Tasks> getTasksById(@PathVariable Integer id) {
        List<Integer> ids;
        ids = List.of(id);
        return service.getTasksWithId(ids);
    }
    
    @GetMapping("status/{status}")
    public ArrayList<Tasks> getTasksByStatus(@PathVariable String status) {
        return service.getTasksByStatus(status);
    }
    
    
}
