package com.platform.common.util;

import com.alibaba.fastjson.JSONObject;

public class JSONUtil {
	
	 
	 
	 public static void main(String[] args) {
			UserVO user = new UserVO();
			user.setUsername("admin");
			user.setInvitedNum(InvitationCode.getInstance().generate("admin"));
			user.setPassword("123456");
			user.setPhone("15529328373");
			System.out.println(JSONObject.toJSONString(user));
		}
}
class UserVO {
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getMyInvitationNum() {
		return myInvitationNum;
	}

	public void setMyInvitationNum(String myInvitationNum) {
		this.myInvitationNum = myInvitationNum;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getInvitedNum() {
		return invitedNum;
	}

	public void setInvitedNum(String invitedNum) {
		this.invitedNum = invitedNum;
	}

	public String getParentUsername() {
		return parentUsername;
	}

	public void setParentUsername(String parentUsername) {
		this.parentUsername = parentUsername;
	}

	private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户级别（权限）
     */
    private Integer priority;
    /**
     * 自己的邀请码
     */
    private String myInvitationNum;
    /**
     * 图像地址
     */
    private String avatarUrl;

    private String invitedNum;

    private String parentUsername;

}