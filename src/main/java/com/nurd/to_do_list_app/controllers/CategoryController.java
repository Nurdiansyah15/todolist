package com.nurd.to_do_list_app.controllers;

import com.nurd.to_do_list_app.domain.models.Category;
import com.nurd.to_do_list_app.services.CategoryService;
import com.nurd.to_do_list_app.utils.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseBuilder.renderJSON(service.findAll(), "categories found successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable @Valid Long id) {
        return ResponseBuilder.renderJSON(service.findById(id), "category found successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Category obj) {
        return ResponseBuilder.renderJSON(service.create(obj), "category created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @Valid @RequestBody Category obj) {
        return ResponseBuilder.renderJSON(service.update(id, obj), "category updated successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        service.deleteById(id);
        return ResponseBuilder.renderJSON(null, "category deleted successfully", HttpStatus.OK);
    }
}
