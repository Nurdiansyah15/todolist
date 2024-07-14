package com.nurd.to_do_list_app.domain.repos;

import com.nurd.to_do_list_app.domain.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

}
