package com.example.task_managment_api.TaskComment;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/comments")
public class TaskCommentController {

    private TaskCommentService service;

    public TaskCommentController(TaskCommentService service) {
        this.service = service;
    }

    @GetMapping
    public List<TaskComment> getAllComments() {
        return service.getAllComments();
    }

    @PostMapping
    public String addCommentToDb(@RequestBody TaskComment comment) {
        return service.addComment(comment);
    }
    
    @GetMapping("task/{taskId}")
    public ArrayList<TaskComment> getComments(@PathVariable Integer taskId) {
        return service.getCommentsWithTaskId(taskId);
    }

    @DeleteMapping("{id}")
    public String deleteComment(@PathVariable Integer id) {
        return service.deleteCommentWithId(id);
    }

}