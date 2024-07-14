package com.nurd.to_do_list_app.services;

import com.nurd.to_do_list_app.domain.models.Category;
import com.nurd.to_do_list_app.domain.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category create(Category obj) {
        return categoryRepo.save(obj);
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category findById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category findByName(String name) {
        return categoryRepo.findByName(name);
    }

    public Category update(Long id, Category obj) {
        Category foundCategory = findById(id);
        foundCategory.setName(obj.getName());
        return categoryRepo.save(foundCategory);
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }
}
