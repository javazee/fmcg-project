package ru.retail.expert.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.retail.expert.exception.ParentException;
import ru.retail.expert.model.Response;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> exceptionResponse(Exception ex) {
        log.error(ex.getMessage(), ex);
        log.warn("Returned response with status -1");
        return ResponseEntity.ok(new Response<>(-1, ex.getMessage()));
    }

    @ExceptionHandler(value = {ParentException.class})
    public ResponseEntity<?> customExceptionResponse(Exception ex) {
        log.error(ex.getMessage());
        log.warn("Returned response with exception");
        return ResponseEntity.ok(new Response<>(-1, ex.getMessage()));
    }
}
