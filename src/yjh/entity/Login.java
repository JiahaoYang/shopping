package yjh.entity;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 登录类
 * @author JiahaoYang
 *
 */
public class Login implements Serializable{


	private static final long serialVersionUID = 1L;
	private String username = "";
	private String backNews = "未登录";
	private LinkedList<String> car = new LinkedList<>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBackNews() {
		return backNews;
	}
	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}
	public LinkedList<String> getCar() {
		return car;
	}
	public void setCar(LinkedList<String> car) {
		this.car = car;
	}
	
}
