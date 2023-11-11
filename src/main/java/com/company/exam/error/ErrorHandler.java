package com.company.exam.error;

import com.company.exam.error.exception.NotFoundException;
import com.company.exam.error.model.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDto handleServiceException(NotFoundException ex) {
        String uuid = UUID.randomUUID().toString();

        log.error("NotFoundException, status: uuid: {}, code: {}, message: {}",
                uuid, BAD_REQUEST, ex.getMessage());

        return ErrorResponseDto.builder()
                .uuid(uuid)
                .code(BAD_REQUEST.value())
                .status(BAD_REQUEST.name())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
