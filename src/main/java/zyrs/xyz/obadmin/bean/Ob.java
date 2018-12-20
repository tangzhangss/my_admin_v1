package zyrs.xyz.obadmin.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目表
 */
public class Ob implements Serializable {

    //序列化ID
    private static final long serialVersionUID = 1;

    private Integer id;
    private String name;//项目名
    private String logo;//项目logo
    private String descr;//项目描述
    private Date createTime;//项目创建时间
    private Date holdDate;//项目维护费用 年算
    private Integer holdCost;//项目要维护金额 年算
    private String holdIns;//项目说明
    private String owner;//主体人|公司
    private Integer userId;//项目所属人Id,账号ID
    private Integer status;//项目状态 1 正常 2 到期

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getHoldDate() {
        return holdDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void setHoldCost(Integer holdCost) {
        this.holdCost = holdCost;
    }

    public void setHoldIns(String holdIns) {
        this.holdIns = holdIns;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public String getDescr() {
        return descr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getHoldCost() {
        return holdCost;
    }

    public String getHoldIns() {
        return holdIns;
    }

    @Override
    public String toString() {
        return "Ob{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", descr='" + descr + '\'' +
                ", createTime=" + createTime +
                ", holdDate=" + holdDate +
                ", holdCost=" + holdCost +
                ", holdIns='" + holdIns + '\'' +
                ", owner='" + owner + '\'' +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}
