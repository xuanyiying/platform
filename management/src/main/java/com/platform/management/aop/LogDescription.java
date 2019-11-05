package com.platform.management.aop;

/**
 * @author wangying Created on 2019/9/29.
 */
enum LogDescription {
	ADD("add#%s add a %s to system."),
	UPDATE("update#%s update the %s to system."),
	DELETE("delete#%s delete a %s from system.");
	/**
	 * description
	 */
	private String description;

	LogDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
