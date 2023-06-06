package com.example.bookgrim.common;

import com.example.bookgrim.common.dto.ExceptionDto;
import com.example.bookgrim.common.exception.BadRequestException;
import com.example.bookgrim.common.exception.BaseRuntimeException;
import com.example.bookgrim.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseRuntimeException.class)
    public ExceptionDto BaseException(BaseRuntimeException exception) {
        return new ExceptionDto(ErrorCode.INTERNAL_SERVER, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto IlllegalStateException(IllegalStateException exception) {
        return new ExceptionDto(ErrorCode.INTERNAL_SERVER, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleBadRequestException(BadRequestException exception) {
        GlobalExceptionHandler.log.error("error message", exception);
        return new ExceptionDto(ErrorCode.INTERNAL_SERVER, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public ExceptionDto IOException(IOException exception) {
        GlobalExceptionHandler.log.error("error message", exception);
        return new ExceptionDto(ErrorCode.INTERNAL_SERVER, exception.getMessage());
    }

//    @ExceptionHandler(value = {Exception.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ExceptionDto unknownException(Exception exception) {
//        GlobalExceptionHandler.log.error("error message", exception);
//        return new ExceptionDto(ErrorCode.INTERNAL_SERVER, exception.getMessage());
//    }
}
