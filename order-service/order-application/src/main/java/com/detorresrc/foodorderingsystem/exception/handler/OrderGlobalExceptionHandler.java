package com.detorresrc.foodorderingsystem.exception.handler;

import com.detorresrc.foodorderingsystem.application.handler.ErrorDTO;
import com.detorresrc.foodorderingsystem.order.service.domain.exception.OrderDomainException;
import com.detorresrc.foodorderingsystem.order.service.domain.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(OrderDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleOrderDomainException(OrderDomainException ex) {
        log.error(ex.getMessage(), ex);

        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(ex.getMessage())
            .build();
    }

    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleOrderNotFoundException(OrderNotFoundException ex) {
        log.error(ex.getMessage(), ex);

        return ErrorDTO.builder()
            .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(ex.getMessage())
            .build();
    }
}
