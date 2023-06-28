/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author mruth
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    //set up the logger for the entire class
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    ResponseEntity<ValidationErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("We have an exception: MethodArgumentNotValidException");
        //let's process
        ValidationErrorMessage vem = new ValidationErrorMessage();
        
        BindingResult br = e.getBindingResult();
        //loop through the field erros, and set the messages
        for (FieldError fe : br.getFieldErrors()) {
            FieldErrorMessage fem = new FieldErrorMessage();
            fem.setField(fe.getField());
            fem.setMessage(fe.getDefaultMessage());
            vem.addFieldErrorMessage(fem);
        }
        
        
        
        return new ResponseEntity(vem,HttpStatus.BAD_REQUEST);
        

    }
    
    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleException(Exception e) {
        logger.error(e + "");
        logger.error(e.getMessage() + "");
        
        logger.error(e.getStackTrace() + "");
        return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        
        
    }
    
    @ExceptionHandler(ArithmeticException.class)
    ResponseEntity<String> handleException(ArithmeticException e) {
        logger.error("Did you divide by zero again moron?");
        logger.error(e + "");
        logger.error(e.getMessage() + "");
        
        
        return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        
        
    }
    
}
