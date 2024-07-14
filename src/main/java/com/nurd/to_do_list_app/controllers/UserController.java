package com.nurd.to_do_list_app.controllers;

import com.nurd.to_do_list_app.services.UserService;
import com.nurd.to_do_list_app.utils.dto.UserDto;
import com.nurd.to_do_list_app.utils.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseBuilder.renderJSON(service.findAll(), "users found successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
        return ResponseBuilder.renderJSON(service.findById(id), "user found successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDto obj) {
        return ResponseBuilder.renderJSON(service.create(obj), "user created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @Valid @RequestBody UserDto obj) {
        return ResponseBuilder.renderJSON(service.update(id, obj), "user updated successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        service.deleteById(id);
        return ResponseBuilder.renderJSON(null, "user deleted successfully", HttpStatus.OK);
    }
}
