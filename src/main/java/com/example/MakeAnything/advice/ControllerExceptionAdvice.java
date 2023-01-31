package com.example.MakeAnything.advice;

import com.example.MakeAnything.domain.common.ApiResponse;
import com.example.MakeAnything.domain.common.exception.BaseException;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import static com.example.MakeAnything.domain.common.exception.type.ErrorCode.*;
import static java.util.stream.Collectors.joining;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 400 BadRequest
     * Spring Validation(@RequestBody)
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ApiResponse<Object> handleMethodValidException(MethodArgumentNotValidException  e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(joining("\n"));
        log.error("MethodArgumentNotValidException : {}", errorMessage);
        return ApiResponse.error(INVALID, errorMessage);
    }

    /**
     * 400 BadRequest
     * Spring Validation(@ModelAttribute)
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ApiResponse<Object> handleBadRequest(BindException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(joining("\n"));
        log.error("BindException: {}", errorMessage);
        return ApiResponse.error(INVALID, errorMessage);
    }

    /**
     * 400 BadRequest
     * RequestParam 필수 필드가 입력되지 않은 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ApiResponse<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn(e.getMessage());
        return ApiResponse.error(INVALID_MISSING_PARAMETER, String.format("필수 파라미터 (%s)를 입력해주세요", e.getParameterName()));
    }

    /**
     * 400 BadRequest
     * RequestPart 필수 필드가 입력되지 않은 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    private ApiResponse<Object> handleMissingServletRequestParameterException(MissingServletRequestPartException e) {
        log.warn(e.getMessage());
        return ApiResponse.error(INVALID_MISSING_PARAMETER, String.format("Multipart (%s)를 입력해주세요", e.getRequestPartName()));
    }

    /**
     * 400 BadRequest
     * RequestPart 필수 Path Variable 가 입력되지 않은 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingPathVariableException.class)
    private ApiResponse<Object> handleMissingPathVariableException(MissingPathVariableException e) {
        log.warn(e.getMessage());
        return ApiResponse.error(INVALID_MISSING_PARAMETER, String.format("Path (%s)를 입력해주세요", e.getVariableName()));
    }

    /**
     * 400 BadRequest
     * 잘못된 타입이 입력된 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    private ApiResponse<Object> handleTypeMismatchException(TypeMismatchException e) {
        log.warn(e.getMessage());
        return ApiResponse.error(INVALID_TYPE, String.format("%s (%s)", INVALID_TYPE.getMessage(), e.getValue()));
    }

    /**
     * 400 BadRequest
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            InvalidFormatException.class,
            ServletRequestBindingException.class
    })
    private ApiResponse<Object> handleInvalidFormatException(Exception e) {
        log.warn(e.getMessage());
        return ApiResponse.error(INVALID);
    }

    /**
     * 405 Method Not Allowed
     * 지원하지 않은 HTTP method 호출 할 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ApiResponse<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.warn(e.getMessage());
        return ApiResponse.error(METHOD_NOT_ALLOWED);
    }

    /**
     * 406 Not Acceptable
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    private ApiResponse<Object> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        log.warn(e.getMessage());
        return ApiResponse.error(NOT_ACCEPTABLE);
    }

    /**
     * 415 UnSupported Media Type
     * 지원하지 않는 미디어 타입인 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeException.class)
    private ApiResponse<Object> handleHttpMediaTypeException(HttpMediaTypeException e) {
        log.warn(e.getMessage(), e);
        return ApiResponse.error(UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * 최대 허용한 이미지 크기를 넘은 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    private ApiResponse<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.error(INVALID_UPLOAD_FILE_SIZE);
    }

    @ExceptionHandler(BaseException.class)
    protected ApiResponse<Object> handleBaseException(BaseException exception) {
        log.error(exception.getMessage(),exception);
        return ApiResponse.error(exception.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    protected ApiResponse<Object> handleException(Exception exception) {
        System.out.println("unhandled exception");
        exception.printStackTrace();
        return ApiResponse.error(BAD_GATEWAY);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ApiResponse<ErrorCode> handleException(DataIntegrityViolationException exception) {
        System.out.println(exception.getLocalizedMessage()
        );
        return ApiResponse.error(CONFLICT);
    }
}
