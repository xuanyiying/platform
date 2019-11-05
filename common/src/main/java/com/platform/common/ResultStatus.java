package com.platform.common;

/**
 * @author wangying
 */
public enum ResultStatus {
    SUCCESS(200, "success"),
    USERNAME_OR_PASSWORD_INVALID(401, "Invalid username or password"),
    USER_NOT_LOGIN(402, "User does not login."),
    USER_NOT_EXIST(403," User does not exist.");
    /**
     * Request status code
     */
    private int code;
    /**
     * message
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
