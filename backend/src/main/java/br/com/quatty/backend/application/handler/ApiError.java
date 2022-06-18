package br.com.quatty.backend.application.handler;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timeStamp;
    private List<String> errors;
}
