package eg.com.inventory.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleInvalidPathVariable(MethodArgumentTypeMismatchException ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Invalid ID format",
                "message", "ID must be a valid integer."
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleEntityNotFound(EntityNotFoundException ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "message", ex.getMessage()
        );
    }

    @ExceptionHandler(InvalidDataFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleInvalidDataFormat(InvalidDataFormatException ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Invalid request body format",
                "message", ex.getMessage()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = ex.getMostSpecificCause().getMessage(); // Extract detailed cause

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.CONFLICT.value(),
                        "error", "Database integrity violation",
                        "message", message
                ));
    }

}
