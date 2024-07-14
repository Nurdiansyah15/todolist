package com.nurd.to_do_list_app.utils.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder {
    public static <T> ResponseEntity<?> renderJSON(T data, String message, HttpStatus httpStatus) {
        ResponseFormat<T> response = ResponseFormat.<T>builder()
                .message(message)
                .status(httpStatus.getReasonPhrase())
                .data(data)
                .build();
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<?> renderError(String message, HttpStatus httpStatus, T errors) {
        ErrorResponseFormat<T> response = ErrorResponseFormat.<T>builder()
                .message(message)
                .status(httpStatus.getReasonPhrase())
                .errors(errors)
                .build();
        return ResponseEntity.status(httpStatus).body(response);
    }

}
