package com.springboot.fullstack.contoller.errorHandler;

import lombok.Data;

@Data
public class ErrorMessage {
    private int statusCode;

    private String message;
    private String description;

    public ErrorMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}