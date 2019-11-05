package com.platform.common.util;

import java.util.Objects;

/**
 * @author wangying Created on 2019/10/23.
 */
public class InvitationCode {
	/** 自定义进制(0,1没有加入,容易与o,l混淆) */
	private static final char[] CHARS = new char[] { 'F', 'L', 'G', 'W', '5', 'X', 'C', '3', 'Z', 'M', '6', '7',
			'Y', 'R', 'T', '2', 'H', '8', 'D', 'V', 'E', 'J', '4', 'K', 'Q', 'P', 'U', 'A', 'N', 'B', '9', 'S'};
	/** 进制长度 */
	private static final int CHARS_LENGTH = CHARS.length;
	/** 随机设定的值 */
	private static final long START_NUMBER = 10242563L;

	private InvitationCode() {
	}

	private static class Holder {
		public static final InvitationCode INSTANCE = new InvitationCode();
	}

	/**
	 *
	 * @param username
	 * @return 随机邀请码
	 */
	public String generate(String username) {
		long hash = Objects.hash(username);
		return toCode(Math.abs(hash));
	}

	private String toCode(long id, long startNumber) {
		if (startNumber < 0) {
			startNumber = START_NUMBER;
		}
		id += startNumber;
		char[] buf = new char[64];
		int charPos = 64;

		while ((id / CHARS_LENGTH) > 0) {
			int ind = (int) (id % CHARS_LENGTH);
			buf[--charPos] = CHARS[ind];
			id /= CHARS_LENGTH;
		}
		buf[--charPos] = CHARS[(int) (id % CHARS_LENGTH)];
		String str = new String(buf, charPos, (64 - charPos));
		return str.toUpperCase();
	}

	private String toCode(long idL) {
		return toCode(idL, START_NUMBER);
	}

	public static InvitationCode getInstance() {
		return Holder.INSTANCE;
	}
}
