package com.taskflow.taskmanagement.handlingExceptions;



import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.query.QueryArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ExceptionHandlerFactory {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleValidationExceptions(AlreadyExistsException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DateValidationException.class)
    public ResponseEntity<?> handleDateValidationException(DateValidationException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<?> handleEmptyException(EmptyException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(DoNotExistException.class)
    public ResponseEntity<?> handleDoNotExistException(DoNotExistException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException exception) {
        return new ResponseEntity<>(List.of(exception.getError()) , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QueryArgumentException.class)
    public ResponseEntity<?> handleQueryArgumentException(QueryArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()) , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<?> handleMissingPathVariableException(MissingPathVariableException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(BadCredentialsException exception) {
        return new ResponseEntity<>(List.of("Invalid username or password") , HttpStatus.BAD_REQUEST);
    }
    // Yeh I know ,  I also do not like this one
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleDHttpMessageNotReadableException(HttpMessageNotReadableException exception) {



//        if(exception.getMessage().contains("not one of the values accepted for Enum class: [CIN, PASSPORT, CARTE_RESIDENCE]")){
//            return new ResponseEntity<>(List.of("Invalid identityDocument value it shoudld be [CIN ,PASSPORT,CARTE_RESIDENCE]") , HttpStatus.BAD_REQUEST);
//        }

        if(exception.getMessage().contains("Failed to deserialize java.time.LocalDate")){
            return new ResponseEntity<>(List.of("Respect the format YYYY-MM-DD") , HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(List.of("Development Purpose #1 , This error Not handled Yet") , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(SQLIntegrityConstraintViolationException e) {


//        if (e.getMessage().contains("foreign key constraint fails") && e.getMessage().contains("FOREIGN KEY (`level_id`) REFERENCES `level` (`id`)")) {
//            return new ResponseEntity<>(List.of("Cannot delete the level because there is a competition associated with it."), HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity<>(List.of("Development Purpose #2 , This error Not handled Yet") , HttpStatus.BAD_REQUEST);
    }

    // Thanks To Yassine Sahyane
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleDateMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        // Return list of errors
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {

        // Return list of errors
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }
}
