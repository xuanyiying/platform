package com.platform.common.exception;

/**
 * @author wangying
 */
public class BaseException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String exceptionName;

    private String errorMessage;

    protected BaseException(String exceptionName, String errorMessage) {
        this.setExceptionName(exceptionName);
        this.setErrorMessage(errorMessage);
    }

    protected BaseException(String errorMessage) {
        this.setErrorMessage(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
