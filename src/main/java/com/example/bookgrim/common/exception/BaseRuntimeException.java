package com.example.bookgrim.common.exception;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BaseRuntimeException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String message;
    public BaseRuntimeException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}