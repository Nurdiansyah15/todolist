package com.nurd.to_do_list_app.services;

import com.nurd.to_do_list_app.domain.models.Category;
import com.nurd.to_do_list_app.domain.models.Comment;
import com.nurd.to_do_list_app.domain.models.Todo;
import com.nurd.to_do_list_app.domain.models.User;
import com.nurd.to_do_list_app.domain.repos.CategoryRepo;
import com.nurd.to_do_list_app.domain.repos.CommentRepo;
import com.nurd.to_do_list_app.domain.repos.TodoRepo;
import com.nurd.to_do_list_app.domain.repos.UserRepo;
import com.nurd.to_do_list_app.utils.dto.UserTodoChekedDto;
import com.nurd.to_do_list_app.utils.dto.UserTodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTodoService {

    private final UserRepo userRepo;
    private final CommentRepo commentRepo;
    private final TodoRepo todoRepo;
    private final CategoryRepo categoryRepo;

    private final TodoService todoService;

    @Autowired
    public UserTodoService(CategoryRepo categoryRepo, TodoService todoService, TodoRepo todoRepo, UserRepo userRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
        this.todoRepo = todoRepo;
        this.categoryRepo = categoryRepo;
        this.todoService = todoService;
    }

    public List<Todo> findAllByUserId(Long id, String search, Boolean completed, String category) {
        List<Todo> todos = todoService.findAll(search, completed, category); // <1>
        List<Todo> result = new ArrayList<>();
        for (var todo : todos) {
            if (todo.getUser().getId().equals(id)) {
                result.add(todo);
            }
        }
        return result;
    }

    public List<Comment> findAllCommentsByUserIdAndTodoId(Long userId, Long todoId) {
        List<Comment> comments = commentRepo.findAll(); // <1>
        List<Comment> result = new ArrayList<>();
        for (var comment : comments) {
            if (comment.getUser().getId().equals(userId) && comment.getTodo().getId().equals(todoId)) {
                result.add(comment);
            }
        }
        return result;
    }

    public Todo createTodoByUserId(Long userId, UserTodoDto todo) {
        Todo newTodo = new Todo();
        newTodo.setTask(todo.getTask());
        newTodo.setDeadline(todo.getDeadline());
        newTodo.setCompleted(false);

        User foundUser = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        newTodo.setUser(foundUser);

        Category foundCategory = categoryRepo.findById(todo.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        newTodo.setCategory(foundCategory);

        return todoRepo.save(newTodo);
    }

    public Todo updateTodoByUserId(Long userId, Long id, UserTodoDto todo) {
        Todo foundTodo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        foundTodo.setTask(todo.getTask());
        foundTodo.setDeadline(todo.getDeadline());

        User foundUser = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        foundTodo.setUser(foundUser);

        Category foundCategory = categoryRepo.findById(todo.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        foundTodo.setCategory(foundCategory);

        return todoRepo.save(foundTodo);
    }

    public Todo checkedTodoByUserId(Long userId, Long id, UserTodoChekedDto todo) {
        Todo foundTodo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        foundTodo.setCompleted(todo.getCompleted());

        User foundUser = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        foundTodo.setUser(foundUser);

        return todoRepo.save(foundTodo);
    }

    public Comment createCommentByUserIdTodoId(Long userId, Long todoId, String comment) {
        Todo foundTodo = todoRepo.findById(todoId).orElseThrow(() -> new RuntimeException("Todo not found"));
        Comment newComment = new Comment();
        newComment.setBody(comment);
        newComment.setTodo(foundTodo);
        newComment.setUser(userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        return commentRepo.save(newComment);
    }

    public Comment updateCommentByUserIdTodoId(Long userId, Long todoId, Long commentId, String comment) {
        Comment foundComment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        foundComment.setBody(comment);

        Todo foundTodo = todoRepo.findById(todoId).orElseThrow(() -> new RuntimeException("Todo not found"));
        foundComment.setTodo(foundTodo);

        User foundUser = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        foundComment.setUser(foundUser);

        return commentRepo.save(foundComment);
    }
}
