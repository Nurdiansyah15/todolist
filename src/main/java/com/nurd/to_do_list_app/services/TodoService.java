package com.nurd.to_do_list_app.services;

import com.nurd.to_do_list_app.domain.models.Category;
import com.nurd.to_do_list_app.domain.models.Todo;
import com.nurd.to_do_list_app.domain.models.User;
import com.nurd.to_do_list_app.domain.repos.CategoryRepo;
import com.nurd.to_do_list_app.domain.repos.TodoRepo;
import com.nurd.to_do_list_app.domain.repos.UserRepo;
import com.nurd.to_do_list_app.utils.dto.TodoDto;
import com.nurd.to_do_list_app.utils.specifications.UserTodoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    public Todo create(TodoDto obj) {
        Todo todo = new Todo();
        todo.setTask(obj.getTask());
        todo.setDeadline(obj.getDeadline());
        todo.setCompleted(obj.getCompleted());

        User foundUser = userRepo.findById(obj.getUserUuid()).orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setUser(foundUser);

        Category foundCategory = categoryRepo.findById(obj.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        todo.setCategory(foundCategory);

        return todoRepo.save(todo);
    }

    public List<Todo> findAll(String search, Boolean completed, String category) {

        Specification<Todo> spec = Specification.where(null); // <1>
        if (search != null && !search.isEmpty()) {
            spec = spec.and(UserTodoSpecification.searchTodo(search));
        }
        if (completed != null) {
            spec = spec.and(UserTodoSpecification.isCompleted(completed));
        }
        if (category != null && !category.isEmpty()) {

            Category foundCategory = categoryRepo.findByName(category);

            if (foundCategory == null) {
                throw new RuntimeException("Category not found");
            }

            spec = spec.and(UserTodoSpecification.findTodoByCategory(foundCategory));
        }

        if (spec != null) {
            return todoRepo.findAll(spec);
        }

        return todoRepo.findAll();
    }

    public List<Todo> findAll() {
        return todoRepo.findAll();
    }

    public Todo findById(Long id) {
        return todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public Todo update(Long id, TodoDto obj) {
        Todo foundTodo = findById(id);
        foundTodo.setTask(obj.getTask());
        foundTodo.setDeadline(obj.getDeadline());
        foundTodo.setCompleted(obj.getCompleted());

        User foundUser = userRepo.findById(obj.getUserUuid()).orElseThrow(() -> new RuntimeException("Todo not found"));
        foundTodo.setUser(foundUser);

        Category foundCategory = categoryRepo.findById(obj.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        foundTodo.setCategory(foundCategory);

        return todoRepo.save(foundTodo);
    }

    public void deleteById(Long id) {
        todoRepo.deleteById(id);
    }
}
