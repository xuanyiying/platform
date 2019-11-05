package com.platform.common.exception;


/**
 * @author wangying
 */
public class AuthFailedException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AuthFailedException(String exceptionName, String errorMessage) {
        super(exceptionName, errorMessage);
    }

    public AuthFailedException(String errorMessage) {
        super(errorMessage);
    }
}
