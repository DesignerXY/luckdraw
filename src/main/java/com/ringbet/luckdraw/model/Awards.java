package com.ringbet.luckdraw.model;

import javax.persistence.*;

public class Awards {
    /**
     * 自增长ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称。如特等奖
     */
    private String name;

    private Integer count;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 说明/奖品
     */
    private String description;

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
     * 获取名称。如特等奖
     *
     * @return name - 名称。如特等奖
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称。如特等奖
     *
     * @param name 名称。如特等奖
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取说明/奖品
     *
     * @return description - 说明/奖品
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置说明/奖品
     *
     * @param description 说明/奖品
     */
    public void setDescription(String description) {
        this.description = description;
    }
}