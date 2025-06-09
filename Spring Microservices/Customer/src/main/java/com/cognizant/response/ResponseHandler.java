package com.cognizant.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message , HttpStatus status,Object responseObj){
        Map<String,Object> map=new HashMap<String ,Object>();
        map.put("Message",message);
        map.put("Status",status.value());
        map.put("Data",responseObj);

        return new ResponseEntity<Object>(map,status);
    }

    //Handling Error messages
    //Dto Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex)
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage); });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
