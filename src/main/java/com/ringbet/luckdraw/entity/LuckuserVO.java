package com.ringbet.luckdraw.entity;

import java.util.Date;

public class LuckuserVO {
	//获奖ID
	private Integer id;
	private Integer lucknum;
	private Date lucktime;
	
	private Integer awardsid;
	private String awardsname;
	private String awardsdesc;
	
	private Integer signinuserid;
	private Date signintime;
	
	private Integer signinid;
	private String signindesc;
	
	private Integer userid;
	//用户名
	private String username;
	//用户头像
	private String headimgurl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLucknum() {
		return lucknum;
	}

	public void setLucknum(Integer lucknum) {
		this.lucknum = lucknum;
	}

	public Integer getAwardsid() {
		return awardsid;
	}

	public void setAwardsid(Integer awardsid) {
		this.awardsid = awardsid;
	}

	public String getAwardsname() {
		return awardsname;
	}

	public void setAwardsname(String awardsname) {
		this.awardsname = awardsname;
	}

	public String getAwardsdesc() {
		return awardsdesc;
	}

	public void setAwardsdesc(String awardsdesc) {
		this.awardsdesc = awardsdesc;
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

	public Integer getSigninid() {
		return signinid;
	}

	public void setSigninid(Integer signinid) {
		this.signinid = signinid;
	}

	public String getSignindesc() {
		return signindesc;
	}

	public Date getLucktime() {
		return lucktime;
	}

	public void setLucktime(Date lucktime) {
		this.lucktime = lucktime;
	}

	public void setSignindesc(String signindesc) {
		this.signindesc = signindesc;
	}

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
	
}
