package MunozJose.Homework2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class ValidationMessage {
    private String message = "Spring Validation Error";
    private ArrayList<FieldErrorMessage> errors = new ArrayList<FieldErrorMessage>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<FieldErrorMessage> getErrors() {
        return errors;
    }
    
    public void setFieldMessages(ArrayList<FieldErrorMessage> errors){
        this.errors = errors;
    }
    
    public void addFieldErrorMessage(FieldErrorMessage message){
        errors.add(message);
    }
    
}
