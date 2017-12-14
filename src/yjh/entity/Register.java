package yjh.entity;

import java.io.Serializable;

public class Register implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userName;
	private String userPwd;
	private String phone;
	private String address;
	private String realName;
	private String backNews = "请注册";
	
	public Register() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getBackNews() {
		return backNews;
	}

	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}

	@Override
	public String toString() {
		return "Register [userName=" + userName + ", userPwd=" + userPwd + ", phone=" + phone + ", address=" + address
				+ ", realName=" + realName + ", backNews=" + backNews + "]";
	}

	
}
