package com.nurd.to_do_list_app.controllers;

import com.nurd.to_do_list_app.services.TodoService;
import com.nurd.to_do_list_app.utils.dto.TodoDto;
import com.nurd.to_do_list_app.utils.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseBuilder.renderJSON(service.findAll(), "todos found successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
        return ResponseBuilder.renderJSON(service.findById(id), "todo found successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TodoDto obj) {
        return ResponseBuilder.renderJSON(service.create(obj), "todo created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @Valid @RequestBody TodoDto obj) {
        return ResponseBuilder.renderJSON(service.update(id, obj), "todo updated successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        service.deleteById(id);
        return ResponseBuilder.renderJSON(null, "todo deleted successfully", HttpStatus.OK);
    }
}
