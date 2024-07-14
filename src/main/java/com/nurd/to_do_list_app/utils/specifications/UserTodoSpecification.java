package com.nurd.to_do_list_app.utils.specifications;

import com.nurd.to_do_list_app.domain.models.Category;
import com.nurd.to_do_list_app.domain.models.Todo;
import org.springframework.data.jpa.domain.Specification;


public class UserTodoSpecification {

    public static Specification<Todo> isCompleted(Boolean completed) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("completed"), completed);
    }

    public static Specification<Todo> searchTodo(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("task"), "%" + search + "%");
    }

    public static Specification<Todo> findTodoByCategory(Category category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }

}
