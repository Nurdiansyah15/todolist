package com.nurd.to_do_list_app.utils.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @NotNull
    private UUID userUuid;
    @NotNull
    private Long todoId;
    @NotNull
    private String body;
}
