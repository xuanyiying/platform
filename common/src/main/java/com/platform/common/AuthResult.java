package com.platform.common;

/**
 * @author wangying
 */
public class AuthResult {
	/**
	 *
	 */
	private int code;
	/**
	 *
	 */
	private String username;
	/**
	 *
	 */
	private String message;
	/**
	 *
	 */
	private Object content;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getContent() {
		return content;
	}

	public AuthResult(int code, String message) {
		this.code = code;
		this.message = message;
		this.content = "";
	}

	public AuthResult(int code, String message, Object content) {
		this.code = code;
		this.message = message;
		this.content = content;
	}

	public AuthResult(ResultStatus status) {
		this.code = status.getCode();
		this.message = status.getMessage();
		this.content = "";
	}

	public AuthResult(ResultStatus status, Object content, String username) {
		this.code = status.getCode();
		this.message = status.getMessage();
		this.content = content;
		this.username = username;
	}

	@Override
	public String toString() {
		return "AuthResult{" + "code=" + code + ", userId='" + username + '\'' + ", message='" + message + '\''
				+ ", content=" + content + '}';
	}

	public AuthResult() {
	}

	public static AuthResult ok(Object content, String username) {
		return new AuthResult(ResultStatus.SUCCESS, content, username);
	}

	public static AuthResult ok() {
		return new AuthResult(ResultStatus.SUCCESS);
	}

	public static AuthResult error(ResultStatus error) {
		return new AuthResult(error);
	}

	public String getUsername() {
		return username;
	}

}
