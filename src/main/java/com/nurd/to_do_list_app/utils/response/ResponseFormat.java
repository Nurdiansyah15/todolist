package com.nurd.to_do_list_app.utils.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseFormat<T> {
    private String message;
    private String status;
    private T data;
}

