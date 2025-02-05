package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ZooGlobalExceptionHandle {
    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(ZooException exception){
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(exception.getHttpStatus().value(), exception.getLocalizedMessage(), System.currentTimeMillis());
        log.error("Exception occured: ", exception);
        return new ResponseEntity<>(zooErrorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception){
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage(), System.currentTimeMillis());
        log.error("Exception occured: ", exception);
        return new ResponseEntity<>(zooErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
