package com.example.demo.common.exception;

import lombok.*;

@Getter
public class Exception extends RuntimeException {

    private final Interface globalInterface;

    public Exception(Interface i) {
        super(i.getMessage());
        this.globalInterface = i;
    }

    public Exception(Interface i, String message) {
        super(i.getMessage() + message);
        this.globalInterface = i;
    }

}
