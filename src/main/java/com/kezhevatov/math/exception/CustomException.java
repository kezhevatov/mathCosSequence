package com.kezhevatov.math.exception;

/**
 * @author Andrey Kezhevatov.
 * Date: 03.13.2017
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -4350630260082656225L;

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
