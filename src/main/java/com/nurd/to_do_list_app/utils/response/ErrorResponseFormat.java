package com.nurd.to_do_list_app.utils.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseFormat<T> {
    private String message;
    private String status;
    private T errors;
}
