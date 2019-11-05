package com.platform.common;

/**
 * @author wangying
 */
public class RequestResult {
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	private RequestResult(boolean success) {
		this.success = success;
	}

	public static RequestResult success() {
		return new RequestResult(true);
	}

	public static RequestResult failed() {
		return new RequestResult(false);
	}
}
