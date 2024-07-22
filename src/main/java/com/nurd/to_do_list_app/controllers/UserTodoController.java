package com.nurd.to_do_list_app.controllers;

import com.nurd.to_do_list_app.services.UserTodoService;
import com.nurd.to_do_list_app.utils.dto.UserTodoChekedDto;
import com.nurd.to_do_list_app.utils.dto.UserTodoDto;
import com.nurd.to_do_list_app.utils.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserTodoController {

    private final UserTodoService service;

    @GetMapping("/{id}/todos")
    public ResponseEntity<?> findAllByUserId(@Valid @PathVariable("id") UUID userUuid, @RequestParam(required = false) String search, @RequestParam(required = false) Boolean completed, @RequestParam(required = false) String category) {
        return ResponseBuilder.renderJSON(service.findAllByUserId(userUuid, search, completed, category), "todos found successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}/{todoId}/comments")
    public ResponseEntity<?> findAllCommentsByUserIdAndTodoId(@Valid @PathVariable("id") UUID userUuid, @Valid @PathVariable Long todoId) {
        return ResponseBuilder.renderJSON(service.findAllCommentsByUserIdAndTodoId(userUuid, todoId), "comments found successfully", HttpStatus.OK);
    }

    @PostMapping("/{id}/todos")
    public ResponseEntity<?> createTodoFromUser(@Valid @PathVariable("id") UUID userUuid, @Valid @RequestBody UserTodoDto todo) {
        return ResponseBuilder.renderJSON(service.createTodoByUserId(userUuid, todo), "todo created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}/todos/{todoId}")
    public ResponseEntity<?> updateTodoFromUser(@Valid @PathVariable("id") UUID userUuid, @Valid @PathVariable Long todoId, @Valid @RequestBody UserTodoDto todo) {
        return ResponseBuilder.renderJSON(service.updateTodoByUserId(userUuid, todoId, todo), "todo updated successfully", HttpStatus.ACCEPTED);
    }

    @PostMapping("/{id}/todos/checked/{todoId}")
    public ResponseEntity<?> checkTodoFromUser(@Valid @PathVariable("id") UUID userUuid, @Valid @PathVariable Long todoId, @Valid @RequestBody UserTodoChekedDto todo) {
        return ResponseBuilder.renderJSON(service.checkedTodoByUserId(userUuid, todoId, todo), "todo checked successfully", HttpStatus.ACCEPTED);
    }

    @PostMapping("{id}/{todoId}/comments")
    public ResponseEntity<?> createCommentFromUser(@Valid @PathVariable("id") UUID userUuid, @Valid @PathVariable Long todoId, @Valid @RequestBody String comment) {
        return ResponseBuilder.renderJSON(service.createCommentByUserIdTodoId(userUuid, todoId, comment), "comment created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{todoId}/comments/{commentId}")
    public ResponseEntity<?> updateCommentFromUser(@Valid @PathVariable("id") UUID userUuid, @Valid @PathVariable Long todoId, @Valid @PathVariable Long commentId, @RequestBody String comment) {
        return ResponseBuilder.renderJSON(service.updateCommentByUserIdTodoId(userUuid, todoId, commentId, comment), "comment updated successfully", HttpStatus.ACCEPTED);
    }

}
