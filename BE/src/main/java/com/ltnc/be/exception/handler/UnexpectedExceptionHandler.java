package com.ltnc.be.exception.handler;

import com.lgsi.lgsibe.dto.error.ErrorResponse;
import com.lgsi.lgsibe.exception.event.log.ExceptionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class UnexpectedExceptionHandler {

    private final ApplicationEventPublisher applicationEventPublisher;

    private void sendLogEvent(Exception exception) {
        applicationEventPublisher.publishEvent(ExceptionEvent.createExceptionEvent(exception));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception) {
        sendLogEvent(exception);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
