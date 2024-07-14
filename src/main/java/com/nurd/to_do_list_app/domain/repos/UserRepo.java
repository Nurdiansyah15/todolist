package com.nurd.to_do_list_app.domain.repos;

import com.nurd.to_do_list_app.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
