package com.ringbet.luckdraw.model;

import java.util.Date;
import javax.persistence.*;

public class Signinuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 签到活动ID
     */
    private Integer signinid;

    /**
     * 账号ID
     */
    private Integer userid;

    /**
     * 第一次签到时间
     */
    private Date createtime;

    /**
     * 最后签到时间
     */
    private Date updatetime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取签到活动ID
     *
     * @return signinid - 签到活动ID
     */
    public Integer getSigninid() {
        return signinid;
    }

    /**
     * 设置签到活动ID
     *
     * @param signinid 签到活动ID
     */
    public void setSigninid(Integer signinid) {
        this.signinid = signinid;
    }

    /**
     * 获取账号ID
     *
     * @return userid - 账号ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置账号ID
     *
     * @param userid 账号ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取第一次签到时间
     *
     * @return createtime - 第一次签到时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置第一次签到时间
     *
     * @param createtime 第一次签到时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取最后签到时间
     *
     * @return updatetime - 最后签到时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置最后签到时间
     *
     * @param updatetime 最后签到时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}