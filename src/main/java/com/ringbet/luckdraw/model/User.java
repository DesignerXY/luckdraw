package com.ringbet.luckdraw.model;

import java.util.Date;
import javax.persistence.*;

public class User {
    /**
     * 自增长ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 显示名字
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 1有效，0无效
     */
    private String status;

    /**
     * 代理编号
     */
    @Column(name = "agent_code")
    private String agentCode;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private Integer subscribe;

    /**
     * 关注时间
     */
    @Column(name = "subscribe_time")
    private Date subscribeTime;

    /**
     * 唯一ID
     */
    private String unionid;

    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openid;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer sex;

    /**
     * 头像图片地址
     */
    private String headimgurl;

    /**
     * 语言，简体中文为zh_CN
     */
    private String language;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;

    /**
     * 用户所在的分组ID
     */
    private Integer groupid;

    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private String privilege;

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
     * 获取显示名字
     *
     * @return name - 显示名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置显示名字
     *
     * @param name 显示名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取来源
     *
     * @return source - 来源
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 设置来源
     *
     * @param source 来源
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 获取电话号码
     *
     * @return mobile - 电话号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置电话号码
     *
     * @param mobile 电话号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取注册时间
     *
     * @return register_time - 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取1有效，0无效
     *
     * @return status - 1有效，0无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置1有效，0无效
     *
     * @param status 1有效，0无效
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取代理编号
     *
     * @return agent_code - 代理编号
     */
    public String getAgentCode() {
        return agentCode;
    }

    /**
     * 设置代理编号
     *
     * @param agentCode 代理编号
     */
    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    /**
     * 获取用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     *
     * @return subscribe - 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    public Integer getSubscribe() {
        return subscribe;
    }

    /**
     * 设置用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     *
     * @param subscribe 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * 获取关注时间
     *
     * @return subscribe_time - 关注时间
     */
    public Date getSubscribeTime() {
        return subscribeTime;
    }

    /**
     * 设置关注时间
     *
     * @param subscribeTime 关注时间
     */
    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    /**
     * 获取唯一ID
     *
     * @return unionid - 唯一ID
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设置唯一ID
     *
     * @param unionid 唯一ID
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * 获取用户的标识，对当前公众号唯一
     *
     * @return openid - 用户的标识，对当前公众号唯一
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户的标识，对当前公众号唯一
     *
     * @param openid 用户的标识，对当前公众号唯一
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @return sex - 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @param sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取头像图片地址
     *
     * @return headimgurl - 头像图片地址
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置头像图片地址
     *
     * @param headimgurl 头像图片地址
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 获取语言，简体中文为zh_CN
     *
     * @return language - 语言，简体中文为zh_CN
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言，简体中文为zh_CN
     *
     * @param language 语言，简体中文为zh_CN
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     *
     * @return remark - 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     *
     * @param remark 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取用户所在的分组ID
     *
     * @return groupid - 用户所在的分组ID
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * 设置用户所在的分组ID
     *
     * @param groupid 用户所在的分组ID
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     *
     * @return privilege - 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * 设置用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     *
     * @param privilege 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}