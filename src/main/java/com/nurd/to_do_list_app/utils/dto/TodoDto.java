package com.nurd.to_do_list_app.utils.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {

    @NotNull
    private String task;

    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date deadline;

    @NotNull
    private Boolean completed;

    @NotNull
    private UUID userUuid;

    @NotNull
    private Long categoryId;
}
