package dev.songpon.exceptiondemo.error;

import exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeError(
            RuntimeException ex, WebRequest request) {
        logger.error("RuntimeException",ex);
        String info =  ((ServletWebRequest)request).getRequest().getRequestURI();
        logger.info(info);
        var version = info.split("/")[1];
        return handleExceptionInternal(ex, encryptErrorMessage(version),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleBusinessError(
            RuntimeException ex, WebRequest request) {
        logger.error("BusinessException");
        String info =  ((ServletWebRequest)request).getRequest().getRequestURI();
        logger.info(info);
        return handleExceptionInternal(ex, "business exception",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ErrorResponse encryptErrorMessage(String version) {
        var error = new ErrorResponse();
        if("v2".equalsIgnoreCase(version)) {
            error.setEncryptedData("jwe");
        } else if ("v1".equalsIgnoreCase(version)) {
            error.setEncryptedData("something else");
        }
        return error;
    }
}
