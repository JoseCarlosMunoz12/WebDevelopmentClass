package MunozJose.Homework2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 *
 * @author jose
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(MethodArgumentNotValidException.class)    
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException e){
        logger.error("We have an exception MethodArgumentNotValidException");
        ValidationMessage message = new ValidationMessage();
        BindingResult br = e.getBindingResult();
        for (FieldError fe : br.getFieldErrors()){
            FieldErrorMessage fem = new FieldErrorMessage();
            fem.setField(fe.getField());
            fem.setMessage(fe.getDefaultMessage());            
            message.addFieldErrorMessage(fem);
        }        
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleE(Exception e){
        String response = e.getLocalizedMessage();
        logger.error("Exception " + e.getClass()+ ":" + response);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
