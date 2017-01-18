package com.ln.entity;

import java.io.Serializable;

/**
 * 登录返回的参数 Author linan Date：2016年6月23日 下午3:27:25
 */
public class LoginInBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// 用户ID
	private String id;
	// 用户名
	private String loginName;
	// 是否是手机 登录
	private String mobileLogin;
	// 当前用户SESSIONID
	private String sessionid;
	// 姓名
	private String name;
	private String type;
	// 登录失败的原因
	private String message;

	private String mobile;

	private String sex;

	private String birthday;

	private String email;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMobileLogin() {
		return mobileLogin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobileLogin(String mobileLogin) {
		this.mobileLogin = mobileLogin;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "LoginInBean [id=" + id + ", loginName=" + loginName
				+ ", mobileLogin=" + mobileLogin + ", sessionid=" + sessionid
				+ ", name=" + name + ", type=" + type + ", message=" + message
				+ "]";
	}

}
