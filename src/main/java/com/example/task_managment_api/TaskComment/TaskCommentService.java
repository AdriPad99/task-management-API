package com.example.task_managment_api.TaskComment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskCommentService {
    
    private TaskCommentRepository repo;

    public TaskCommentService(TaskCommentRepository repo) {
        this.repo = repo;
    }

    public List<TaskComment> getAllComments() {
        return repo.findAll();
    }

    public boolean idIsInDb(Integer id) {
        List<TaskComment> comments = getAllComments();

        for (TaskComment comment: comments) {
            if (comment.getId() == id) {
                return true;
            }
        }

        return false;
    }

    public String addComment(TaskComment comment) {
        repo.save(comment);
        return "Comment saved!";
    }

    public ArrayList<TaskComment> getCommentsWithTaskId(Integer id) {
        List<TaskComment> comments = getAllComments();

        ArrayList<TaskComment> neededComments;
        neededComments = new ArrayList<>();

        for (TaskComment comment: comments) {
            if (comment.getTaskId() == id) {
                neededComments.add(comment);
            }
        }

        return neededComments;
    }

    public String deleteCommentWithId(Integer id) {
        if (idIsInDb(id)){
            repo.deleteById(id);
            return "Comment with ID of " + id + " was deleted!";
        } else {
            return "Given ID of " + id + " is not within the db.";
        }
    }
}
