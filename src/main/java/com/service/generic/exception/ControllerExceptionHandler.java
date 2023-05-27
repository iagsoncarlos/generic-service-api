package com.service.generic.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.PropertyValueException;
import org.postgresql.util.PSQLException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
@ControllerAdvice
@Component
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<ApiError.Field> fields = new ArrayList<>();

        for(ObjectError error : exception.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fields.add(new ApiError.Field(name, message));
        }

        ApiError apiError = new ApiError();
        apiError.setStatus(status.value());
        apiError.setDateTime(LocalDateTime.now());
        apiError.setMessage("Please fill in required fields");
        apiError.setFields(fields);

        return handleExceptionInternal(exception, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        String message = "";

        Throwable cause = exception.getCause();
        if (cause instanceof JsonParseException jsonParseException){
            message = jsonParseException.getOriginalMessage();

        } else if (cause instanceof MismatchedInputException mismatchedInputException) {
            if (mismatchedInputException.getPath() != null && mismatchedInputException.getPath().size() > 0){
                message = "Invalid request field: " +  mismatchedInputException.getPath().get(0).getFieldName();
            }else{
                message = "Invalid request message";
            }
        } else if (cause instanceof JsonMappingException jsonMappingException){
            message = jsonMappingException.getOriginalMessage();

            if (jsonMappingException.getPath() != null && jsonMappingException.getPath().size() > 0){
                message = "Invalid request field: " +  jsonMappingException.getPath().get(0).getFieldName();
            }
        }

        ApiError apiError = new ApiError();
        apiError.setStatus(400);
        apiError.setDateTime(LocalDateTime.now());
        apiError.setMessage(message);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException exception) {

        ApiError apiError = new ApiError();
        apiError.setStatus(400);
        apiError.setDateTime(LocalDateTime.now());
        apiError.setMessage(exception.getMessage());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handlePropertyValueException(PropertyValueException exception) {

        List<ApiError.Field> fields = new ArrayList<>();

        String name = exception.getPropertyName();
        String message = exception.getMessage();
        fields.add(new ApiError.Field(name, message));

        ApiError apiError = new ApiError();
        apiError.setStatus(400);
        apiError.setDateTime(LocalDateTime.now());
        apiError.setMessage("Please fill in the required fields");
        apiError.setFields(fields);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException exception) {

        List<ApiError.Field> fields = new ArrayList<>();

        for (ConstraintViolation constraintViolation : exception.getConstraintViolations()) {
            String name = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            fields.add(new ApiError.Field(name, message));
        }

        ApiError apiError = new ApiError();
        apiError.setStatus(400);
        apiError.setDateTime(LocalDateTime.now());
        apiError.setMessage("Please fill in the required fields");
        apiError.setFields(fields);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

        List<ApiError.Field> fields = new ArrayList<>();

        Throwable throwable = exception.getMostSpecificCause();

        if (throwable instanceof PSQLException psqlException) {
            var serverErrorMessage = psqlException.getServerErrorMessage();

            if (serverErrorMessage != null) {
                var detail = serverErrorMessage.getDetail();
                throwable = new Exception(detail);
            }

            String name = throwable.getMessage()
                    .trim().substring(5)
                    .replace("=", " ")
                    .replace("(", "").split("\\)")[0];

            String message = StringUtils.capitalize(name.concat(" already exists."));

            fields.add(new ApiError.Field(name, message));
        }

        ApiError apiError = new ApiError();
        apiError.setStatus(409);
        apiError.setDateTime(LocalDateTime.now());
        apiError.setMessage("Please replace required fields");
        apiError.setFields(fields);

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
}
