package com.kezhevatov.math.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.kezhevatov.math.exception.ErrorCode.UNEXPECTED_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Andrey Kezhevatov.
 * Date: 03.13.2017
 */
@ControllerAdvice
@ResponseStatus(BAD_REQUEST)
@ResponseBody
public class CommonExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ErrorResponse(new CustomException(UNEXPECTED_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(CustomException.class)
    public ErrorResponse handleCustomException(CustomException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ErrorResponse(ex);
    }
}
