package com.ringbet.luckdraw.entity;

import java.util.Date;

public class SigninuserVO {
	
	private Integer signinuserid;
	private Date signintime;
	
	private Integer signinid;
	private String signindesc;
	
	private Integer userid;
	//用户名
	private String username;
	//用户头像
	private String headimgurl;
	public Integer getSigninuserid() {
		return signinuserid;
	}
	public void setSigninuserid(Integer signinuserid) {
		this.signinuserid = signinuserid;
	}
	public Date getSignintime() {
		return signintime;
	}
	public void setSignintime(Date signintime) {
		this.signintime = signintime;
	}
	public Integer getSigninid() {
		return signinid;
	}
	public void setSigninid(Integer signinid) {
		this.signinid = signinid;
	}
	public String getSignindesc() {
		return signindesc;
	}
	public void setSignindesc(String signindesc) {
		this.signindesc = signindesc;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	
}
