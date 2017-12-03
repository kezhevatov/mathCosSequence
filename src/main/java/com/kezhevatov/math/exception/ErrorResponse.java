package com.kezhevatov.math.exception;

/**
 * @author Andrey Kezhevatov.
 * Date: 03.12.2017
 */
public class ErrorResponse {
    private final String message;

    private final ErrorCode errorCode;

    public ErrorResponse(CustomException ex) {
        message = ex.getMessage();
        errorCode = ex.getErrorCode();
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
