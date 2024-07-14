package com.nurd.to_do_list_app.services;

import com.nurd.to_do_list_app.domain.models.Comment;
import com.nurd.to_do_list_app.domain.models.Todo;
import com.nurd.to_do_list_app.domain.models.User;
import com.nurd.to_do_list_app.domain.repos.CommentRepo;
import com.nurd.to_do_list_app.domain.repos.TodoRepo;
import com.nurd.to_do_list_app.domain.repos.UserRepo;
import com.nurd.to_do_list_app.utils.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;


    public Comment create(CommentDto obj) {
        Comment comment = new Comment();
        comment.setBody(obj.getBody());

        Todo foundTodo = todoRepo.findById(obj.getTodoId()).orElseThrow(() -> new RuntimeException("Todo not found"));
        comment.setTodo(foundTodo);

        User foundUser = userRepo.findById(obj.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        comment.setUser(foundUser);

        return commentRepo.save(comment);
    }

    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    public Comment findById(Long id) {
        return commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public Comment update(Long id, CommentDto obj) {
        Comment foundComment = findById(id);
        foundComment.setBody(obj.getBody());

        Todo foundTodo = todoRepo.findById(obj.getTodoId()).orElseThrow(() -> new RuntimeException("Todo not found"));
        foundComment.setTodo(foundTodo);

        User foundUser = userRepo.findById(obj.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        foundComment.setUser(foundUser);

        return commentRepo.save(foundComment);
    }

    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }
}
