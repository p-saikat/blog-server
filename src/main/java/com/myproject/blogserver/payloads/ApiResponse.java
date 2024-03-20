package com.myproject.blogserver.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private Boolean success;
    private Object payload;

    public ApiResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
