package cn.javakk.entity;

import cn.javakk.util.DateUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @Author: javakk
 * @Date: 2019/4/14 14:41
 */
@Entity
@Table(name="appliedInfo")
public class AppliedInfo {

    @Id
    private String id;

    /**
     * 投递人
     */
    private String userId;
    /**
     * 所属职位
     */
    private String positionId;
    /**
     * 所属公司
     */
    private String companyId;
    /**
     * 投递状态
     */
    private Integer appliedStatus;
    private Integer status;
    private java.util.Date createTime;
    private java.util.Date modifyTime;

    @Transient
    private String timePassed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getAppliedStatus() {
        return appliedStatus;
    }

    public void setAppliedStatus(Integer appliedStatus) {
        this.appliedStatus = appliedStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getTimePassed() {
        return DateUtil.passedString(this.createTime);
    }
}
