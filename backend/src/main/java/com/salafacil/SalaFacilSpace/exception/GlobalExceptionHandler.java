package com.salafacil.SalaFacilSpace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProfessorNotFoundException.class)
    public ResponseEntity<?> handleProfessorNotFound(ProfessorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status", 404,
                "erro", ex.getMessage()
            )
        );
    }

}
