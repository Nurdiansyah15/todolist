package com.nurd.to_do_list_app.domain.repos;

import com.nurd.to_do_list_app.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository <User, UUID> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
