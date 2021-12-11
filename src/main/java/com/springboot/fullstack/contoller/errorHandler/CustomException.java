package com.springboot.fullstack.contoller.errorHandler;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private String txtMessage;

    public CustomException(String errorMessage) {
        super(errorMessage);
        txtMessage = errorMessage;
    }
}