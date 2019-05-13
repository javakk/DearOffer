package cn.javakk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: JavaKK
 * @Date: 2019/4/28 13:59
 */

@Entity
@Table(name = "company")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Company {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    private String logo;

    private String shortName;

    private String financingTag;

    private String companyScaleTag;

    private String industryTag;

    private String welfareTag;

    private String introduction;

    private String name;

    private String registerPerson;

    private String registerMoney;

    private String registerTime;

    private String companyType;

    private String companyStatus;

    private String address;

    private String registerCode;

    private String sellScale;

    private Date createDate;

    private String source;

    private String sourceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFinancingTag() {
        return financingTag;
    }

    public void setFinancingTag(String financingTag) {
        this.financingTag = financingTag;
    }

    public String getCompanyScaleTag() {
        return companyScaleTag;
    }

    public void setCompanyScaleTag(String companyScaleTag) {
        this.companyScaleTag = companyScaleTag;
    }

    public String getIndustryTag() {
        return industryTag;
    }

    public void setIndustryTag(String industryTag) {
        this.industryTag = industryTag;
    }

    public String getWelfareTag() {
        return welfareTag;
    }

    public void setWelfareTag(String welfareTag) {
        this.welfareTag = welfareTag;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisterPerson() {
        return registerPerson;
    }

    public void setRegisterPerson(String registerPerson) {
        this.registerPerson = registerPerson;
    }

    public String getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(String registerMoney) {
        this.registerMoney = registerMoney;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getSellScale() {
        return sellScale;
    }

    public void setSellScale(String sellScale) {
        this.sellScale = sellScale;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Company(String logo, String shortName, String financingTag, String companyScaleTag, String industryTag, String welfareTag, String introduction, String name, String registerPerson, String registerMoney, String registerTime, String companyType, String companyStatus, String address, String registerCode, String sellScale, Date createDate, String source, String sourceId) {
        this.logo = logo;
        this.shortName = shortName;
        this.financingTag = financingTag;
        this.companyScaleTag = companyScaleTag;
        this.industryTag = industryTag;
        this.welfareTag = welfareTag;
        this.introduction = introduction;
        this.name = name;
        this.registerPerson = registerPerson;
        this.registerMoney = registerMoney;
        this.registerTime = registerTime;
        this.companyType = companyType;
        this.companyStatus = companyStatus;
        this.address = address;
        this.registerCode = registerCode;
        this.sellScale = sellScale;
        this.createDate = createDate;
        this.source = source;
        this.sourceId = sourceId;
    }

    public Company(){}
}
