package com.nurd.to_do_list_app.controllers;

import com.nurd.to_do_list_app.services.CommentService;
import com.nurd.to_do_list_app.utils.dto.CommentDto;
import com.nurd.to_do_list_app.utils.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseBuilder.renderJSON(service.findAll(), "comments found successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
        return ResponseBuilder.renderJSON(service.findById(id), "comment found successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CommentDto obj) {
        return ResponseBuilder.renderJSON(service.create(obj), "comment created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @Valid @RequestBody CommentDto obj) {
        return ResponseBuilder.renderJSON(service.update(id, obj), "comment updated successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        service.deleteById(id);
        return ResponseBuilder.renderJSON(null, "comment deleted successfully", HttpStatus.OK);
    }
}
