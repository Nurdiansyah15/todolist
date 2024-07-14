package com.nurd.to_do_list_app.domain.repos;

import com.nurd.to_do_list_app.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM categories WHERE name = ?1", nativeQuery = true)
    Category findByName(String name);
}
