package com.sekretowicz.computer_shop.controller;

import com.sekretowicz.computer_shop.dto.ErrorDto;
import com.sekretowicz.computer_shop.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;

@RestControllerAdvice()
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundHandler(NotFoundException ex) {
        System.out.println(ex.getMessage());
        return new ErrorDto(404, "Not Found", LocalDateTime.now());
    }

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorDto badGatewayHandler(RestClientException ex) {
        System.out.println(ex.getMessage());
        return new ErrorDto(502, ex.getMessage(), LocalDateTime.now());
    }
}