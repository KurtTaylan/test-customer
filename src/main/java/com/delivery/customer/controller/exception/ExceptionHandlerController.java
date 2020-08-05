package com.delivery.customer.controller.exception;

import com.delivery.customer.model.dto.Error;
import com.delivery.customer.model.exception.BusinessException;
import com.delivery.customer.model.exception.ServerException;
import com.delivery.customer.model.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.BindException;


@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex) {
        log.error("Validation exception occurred: ", ex);
        Error error = new Error();
        error.setMessage("Validation Problem Occurred");
        error.setDetail(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        log.error("Business exception occurred: ", ex);
        Error error = new Error();
        error.setMessage("Business Problem Occurred");
        error.setDetail(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException exception occurred: ", ex);
        Error error = new Error();
        error.setMessage("MethodArgumentNotValidException Problem Occurred");
        error.setDetail(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> handleBindException(BindException ex) {
        log.error("BindException exception occurred: ", ex);
        Error error = new Error();
        error.setMessage("BindException Problem Occurred");
        error.setDetail(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerException.class)
    protected ResponseEntity<Object> handleBusinessException(ServerException ex) {
        log.error("Server exception occurred: ", ex);
        Error error = new Error();
        error.setMessage("Server Problem Occurred");
        error.setDetail(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
