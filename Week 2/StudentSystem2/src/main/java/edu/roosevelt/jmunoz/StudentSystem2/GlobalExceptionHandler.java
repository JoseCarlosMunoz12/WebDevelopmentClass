/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.jmunoz.StudentSystem2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author jose
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private Logger logger2 = LoggerFactory.getLogger("GlobalExceptionHandler");
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleAE(ArithmeticException e){
        String response = e.getLocalizedMessage();
        logger.error("Arithmetic Exception: " + response);
        logger2.error("Arithmetic Exception: " + response);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRE(RuntimeException e){
        String response = e.getLocalizedMessage();
        logger.error("Runtime Exception: " + response);
        logger2.error("Runtime Exception: " + response);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleE(Exception e){
        String response = e.getLocalizedMessage();
        logger.error("Exception: " + response);
        logger2.error("Exception: " + response);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
