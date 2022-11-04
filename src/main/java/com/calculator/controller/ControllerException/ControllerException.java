package com.calculator.controller.ControllerException;

import com.calculator.exception.CalculatorBasicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerException {

    private static final String MESSAGE_ERROR = "Hubo un error,por favor contacte al administrador";
    private static final Map<String, Integer> RESPONSE_STATUS_HTTP = new HashMap<>();

    public ControllerException() {
        RESPONSE_STATUS_HTTP.put(CalculatorBasicException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> mostrarExcepcion(Exception exception){
        ResponseEntity<String> result;
        String nameExeption=exception.getClass().getSimpleName();
        String message=exception.getMessage();
        Integer code= RESPONSE_STATUS_HTTP.get(nameExeption);
        if(code!=null){

            result=new ResponseEntity<>(message,HttpStatus.valueOf(code));
        }else{

            result=new ResponseEntity<>(MESSAGE_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
