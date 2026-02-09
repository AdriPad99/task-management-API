package com.example.task_managment_api.Task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TasksService {
    
    private final TasksRepository repo;

    public TasksService(TasksRepository repo) {
        this.repo = repo;
    }

    
    public List<Tasks> getAllTasks() {
        return repo.findAll();
    }
    
    public boolean taskIdExists(Integer id) {

        List<Tasks> tasks = getAllTasks();

        for (Tasks task: tasks) {
            if(task.getId() == id) {
                return true;
            }
        }

        return false;
    }

    public String addTask(Tasks task) {
        if(task.getTitle().isEmpty()) {
            return "Please enter a valid title.";
        } else if (task.getDescription().isEmpty()) {
            return "Please enter a valid description.";
        } else if (!task.getStatus().equalsIgnoreCase("TODO") && !task.getStatus().equalsIgnoreCase("IN_PROGRESS") && !task.getStatus().equalsIgnoreCase("COMPLETED")) {
            return "Please enter a valid task status: (TODO/IN_PROGRESS/COMPLETED)";
        } else if (!task.getPriority().equalsIgnoreCase("LOW") && !task.getPriority().equalsIgnoreCase("MEDIUM") && !task.getPriority().equalsIgnoreCase("HIGH")) {
            return "Please enter a valid task priortity: (LOW, MEDIUM, HIGH)";
        } else if (task.getAssignedUserId() < 1) {
            return "Please enter valid Assigned user Id: (Greater than 1)";
        } else {
            repo.save(task);
            return "Task saved successfully!";
        }
    }

    public List<Tasks> getTasksWithId(List<Integer> id) {
        List<Tasks> tasks;

        tasks = repo.findAllById(id);
        return tasks;
    }

    public ArrayList<Tasks> getTasksByStatus(String status) {

        List<Tasks> tasks = getAllTasks();
        ArrayList<Tasks> tasksWithCurrentStatus;
        tasksWithCurrentStatus = new ArrayList<>();

        for (Tasks task : tasks) {
            if (task.getStatus().equals(status)) {
                tasksWithCurrentStatus.add(task);
            }
        }

        return tasksWithCurrentStatus;
    }

    public ArrayList<Tasks> getTasksWithUserId(Integer id) {

        List<Tasks> tasks = getAllTasks();

        ArrayList<Tasks> userTasks;
        userTasks = new ArrayList<>();

        for (Tasks task: tasks) {
            if(task.getAssignedUserId() == id) {
                userTasks.add(task);
            }
        }

        return userTasks;
    }

    public String updateTaskWithId(Integer id, Tasks task) {

        if (taskIdExists(id)) {
            Tasks userTask = new Tasks(
                id, 
                task.getTitle(), 
                task.getDescription(), 
                task.getStatus(), 
                task.getPriority(), 
                task.getAssignedUserId()
            );
            repo.save(userTask);
    
            return "updated task " + id + " successfully!";
        } else {
            return "Task id " + id + " does not exist in the db.";
        }

    }

    public String deleteTaskWithId(Integer id) {

        if (taskIdExists(id)) {
            List<Tasks> userTasks = getTasksWithId(List.of(id));
    
            repo.delete(userTasks.get(0));
    
            return "User Task with ID of " + id + " was deleted!";
        } else {
            return "Task id " + id + " does not exist in the db.";
        }
        
    }
}
