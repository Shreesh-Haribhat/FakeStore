package com.shreesh.fakestore.ControllerAdvice;

import com.shreesh.fakestore.Exception.InvalidProductIdException;
import com.shreesh.fakestore.dtos.ErrorResponseDTO;
import com.shreesh.fakestore.dtos.ProductWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.InvalidClassException;

@RestControllerAdvice
public class GlobalExceptiionHandler {

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidProductIdException()
    {
        return new ResponseEntity<>(new ErrorResponseDTO("Invalid Product"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDTO> handleAirthmeticException()
    {
        return new ResponseEntity<>(new ErrorResponseDTO("Divide By zero from contrller"),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
