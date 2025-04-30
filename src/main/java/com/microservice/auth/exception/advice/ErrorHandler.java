package com.microservice.auth.exception.advice;

import com.microservice.auth.dto.ErrorDTO;
import com.microservice.auth.exception.exceptiongeneric.NotUsernameFoundException;
import com.microservice.auth.util.Constantes;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> runtimeEceptionHandler(RuntimeException ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(Constantes.CODIGO_ERROR_GENERAL)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotUsernameFoundException.class)
    public ResponseEntity<ErrorDTO> usernameNotFoundExceptionHandler(NotUsernameFoundException ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, ex.getHttpStatus());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> illegalArgumentExceptionHandler(IllegalArgumentException ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(Constantes.CODIGO_ERROR_PARTICULAR)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException  ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(Constantes.CODIGO_ERROR_PARTICULAR)
                .message(ex.getMessage())
                .build();
        if (ex.getMessage().contains("Duplicate entry"))
            errorDTO.setMessage(Constantes.ERROR_EMAIL_REPETIDA);
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorDTO> nullPointerExceptionHandler(NullPointerException  ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(Constantes.CODIGO_ERROR_PARTICULAR)
                .message(ex.getMessage())
                .build();
        if (ex.getMessage().contains("because \"password\" is null"))
            errorDTO.setMessage(Constantes.ERROR_FALTA_PASSWORD);
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException  ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(Constantes.CODIGO_ERROR_PARTICULAR)
                .message(ex.getMessage())
                .build();
        if (ex.getMessage().contains("default message [password]]")){
            errorDTO.setMessage("datos incompletos falta contraseña");
        }else if (ex.getMessage().contains("default message [email]]") || ex.getMessage().contains(
                "default message [debe ser una dirección de correo electrónico con formato correcto]]")){
            errorDTO.setMessage(Constantes.ERROR_FALTA_EMAIL);
        }else if (ex.getMessage().contains("default message [firsName]]")){
            errorDTO.setMessage(Constantes.ERROR_FALTA_FIRS_NAME);
        }else if (ex.getMessage().contains("default message [lastName]]")){
            errorDTO.setMessage(Constantes.ERROR_FALTA_LAST_NAME);
        }else if (ex.getMessage().contains("default message [numDocument]]")){
            errorDTO.setMessage(Constantes.ERROR_FALTA_NUM_DOCUMENT);
        }else if (ex.getMessage().contains("default message [phone]]")){
            errorDTO.setMessage(Constantes.ERROR_FALTA_NUM_PHONE);
        }
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> constraintViolationExceptionHandler(ConstraintViolationException  ex){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(Constantes.CODIGO_ERROR_PARTICULAR)
                .message(ex.getMessage())
                .build();
        if (ex.getMessage().contains("propertyPath=email")){
            errorDTO.setMessage(Constantes.ERROR_EMAIL_ERRADO);
        }
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}