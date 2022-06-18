package br.com.quatty.backend.application.handler;

import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error: ex.getBindingResult().getGlobalErrors()){
            errors.add(error.getObjectName() + ": "+ error.getDefaultMessage());
        }
        ApiError apiError =  ApiError.builder()
                .httpStatus(badRequest)
                .message(ex.getLocalizedMessage())
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .errors(errors)
                .build();
        return handleExceptionInternal(ex, apiError, headers, badRequest, request);
    }


    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiError apiError = ApiError.builder()
                .httpStatus(badRequest)
                .message("Entity not found")
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();

        return new ResponseEntity<>(apiError, badRequest);
    }
}
