package cn.javakk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 * @author JavaKK
 *
 */
@Entity
@Table(name="teachIn")
public class TeachIn implements Serializable{

    @Id
    private String id;


    /**
     * 公司名
     */
    private String companyName;
    /**
     * 学校名
     */
    private String schoolName;
    /**
     * 招聘内容
     */
    private String content;
    /**
     * 地址
     */
    private String address;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 入库时间
     */
    private java.util.Date createTime;
    private java.util.Date modifyTime;
    private Integer status;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(java.util.Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }



}
