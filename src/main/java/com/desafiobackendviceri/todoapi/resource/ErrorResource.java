package com.desafiobackendviceri.todoapi.resource;

import com.desafiobackendviceri.todoapi.dto.response.ErrorMessageResponse;
import com.desafiobackendviceri.todoapi.exception.ForbiddenOperation;
import com.desafiobackendviceri.todoapi.exception.ResourceAlreadyExistent;
import com.desafiobackendviceri.todoapi.exception.TaskNotFoundException;
import com.desafiobackendviceri.todoapi.exception.UnsafePasswordException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorResource extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistent.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse ResourceAlreadyExistent(Exception erro, WebRequest req){
        return new ErrorMessageResponse(400,"The email informed already exists on database.Please insert another","Registry Duplicated","Bad Request Exception");
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse TaskNotFounf(Exception erro, WebRequest req){
        return new ErrorMessageResponse(404, erro.getMessage(), "Task not found","Not Found Exception");
    }

    @ExceptionHandler(UnsafePasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse UnsafePassword(Exception erro, WebRequest req){
        return new ErrorMessageResponse(400,erro.getMessage(),"Unsafe Password","Unsafe Password Exception");
    }

    @ExceptionHandler(ForbiddenOperation.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessageResponse ForbiddenOperation(Exception erro, WebRequest req){
        return new ErrorMessageResponse(403, erro.getMessage(), "Forbidden","Forbidden Operation Exception");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageResponse internalServerError(Exception erro, WebRequest req){
        return new ErrorMessageResponse(500, erro.getMessage(), "Internal Server Error","Internal Server Error Exception");
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(400, ex.getFieldError().getDefaultMessage(), "Invalid Request","Invalid Request Exception");
        return handleExceptionInternal(ex, errorMessageResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

}
