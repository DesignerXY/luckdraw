package com.ringbet.luckdraw.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Luckuser {
    /**
     * 自增长ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 奖项ID
     */
    private Integer awardsid;

    /**
     * 用户签到ID
     */
    private Integer signinuserid;

    /**
     * 中奖号码
     */
    private Integer number;

    private Date createtime;

    /**
     * 获取自增长ID
     *
     * @return id - 自增长ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增长ID
     *
     * @param id 自增长ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取奖项ID
     *
     * @return awardsid - 奖项ID
     */
    public Integer getAwardsid() {
        return awardsid;
    }

    /**
     * 设置奖项ID
     *
     * @param awardsid 奖项ID
     */
    public void setAwardsid(Integer awardsid) {
        this.awardsid = awardsid;
    }

    /**
     * 获取用户ID
     *
     * @return userid - 用户ID
     */
    public Integer getSigninuserid() {
        return signinuserid;
    }

    /**
     * 设置用户ID
     *
     * @param userid 用户ID
     */
    public void setSigninuserid(Integer signinuserid) {
        this.signinuserid = signinuserid;
    }

    /**
     * 获取中奖号码
     *
     * @return number - 中奖号码
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置中奖号码
     *
     * @param number 中奖号码
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}