package com.nurd.to_do_list_app.domain.repos;

import com.nurd.to_do_list_app.domain.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {
}
