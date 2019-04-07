package cn.javakk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;

/**
 * 实体类
 * @author javakk
 *
 */
@Entity
@Table(name="company")
public class Company implements Serializable{

	@Id
	private String id;


	/**
	 * 名称
	 */
	private String name;
	/**
	 * logo
	 */
	private String logo;
	/**
	 * 简称
	 */
	private String shortName;
	/**
	 * 行业字典
	 */
	private String industryTag;
	/**
	 * 规模字典
	 */
	private String companyScaleTag;
	/**
	 * 融资字典
	 */
	private String financingTag;
	/**
	 * 官网
	 */
	private String website;
	/**
	 * 简介
	 */
	private String introduction;
	/**
	 * 福利字典
	 */
	private String welfareTag;
	/**
	 * 上班时间
	 */
	private Time workTimeStart;
	/**
	 * 下班时间
	 */
	private Time workTimeEnd;
	/**
	 * 加班字典
	 */
	private String overtimeTag;
	/**
	 * 注册日期
	 */
	private java.util.Date registerTime;
	/**
	 * 统一代码
	 */
	private String registerCode;
	/**
	 * 法人
	 */
	private String registerPerson;
	/**
	 * 通信地址
	 */
	private String address;
	/**
	 * 注册地址
	 */
	private String registerCapital;
	private Long likedCount;
	private Long pageView;
	/**
	 * 综合得分
	 */
	private Float score;
	/**
	 * 来源字典(1爬虫2填写)
	 */
	private Integer source;
	private Integer status;
	private java.util.Date createTime;
	private java.util.Date modifyTime;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getIndustryTag() {		
		return industryTag;
	}
	public void setIndustryTag(String industryTag) {
		this.industryTag = industryTag;
	}

	public String getCompanyScaleTag() {		
		return companyScaleTag;
	}
	public void setCompanyScaleTag(String companyScaleTag) {
		this.companyScaleTag = companyScaleTag;
	}

	public String getFinancingTag() {		
		return financingTag;
	}
	public void setFinancingTag(String financingTag) {
		this.financingTag = financingTag;
	}

	public String getWebsite() {		
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIntroduction() {		
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getWelfareTag() {		
		return welfareTag;
	}
	public void setWelfareTag(String welfareTag) {
		this.welfareTag = welfareTag;
	}

	public Time getWorkTimeStart() {
		return workTimeStart;
	}
	public void setWorkTimeStart(Time workTimeStart) {
		this.workTimeStart = workTimeStart;
	}

	public Time getWorkTimeEnd() {
		return workTimeEnd;
	}
	public void setWorkTimeEnd(Time workTimeEnd) {
		this.workTimeEnd = workTimeEnd;
	}

	public String getOvertimeTag() {		
		return overtimeTag;
	}
	public void setOvertimeTag(String overtimeTag) {
		this.overtimeTag = overtimeTag;
	}

	public java.util.Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterCode() {
		return registerCode;
	}
	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public String getRegisterPerson() {
		return registerPerson;
	}
	public void setRegisterPerson(String registerPerson) {
		this.registerPerson = registerPerson;
	}

	public String getAddress() {		
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegisterCapital() {
		return registerCapital;
	}
	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	public Long getLikedCount() {		
		return likedCount;
	}
	public void setLikedCount(Long likedCount) {
		this.likedCount = likedCount;
	}

	public Long getPageView() {		
		return pageView;
	}
	public void setPageView(Long pageView) {
		this.pageView = pageView;
	}

	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getSource() {		
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getStatus() {		
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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


	
}
