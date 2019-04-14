package cn.javakk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author javakk
 *
 */
@Entity
@Table(name="position")
public class Position implements Serializable{

	@Id
	private String id;


	/**
	 * 发布者
	 */
	private String publisherId;
	/**
	 * 职位类型字典
	 */
	private String positionTag;
	/**
	 * 所在城市字典
	 */
	private String cityTag;
	/**
	 * 工作时间字典
	 */
	private String experienceTag;
	/**
	 * 学历字典
	 */
	private String degreeTag;
	/**
	 * 薪资起点
	 */
	private String salaryLow;
	/**
	 * 薪资终点
	 */
	private String salaryHigh;
	/**
	 * 技能字典
	 */
	private String skillTag;
	/**
	 * 具体描述
	 */
	private String description;
	/**
	 * 附件说明
	 */
	private String attachment;
	/**
	 * 具体人数
	 */
	private Integer total;
	/**
	 * 申请开始
	 */
	private java.util.Date applyStart;
	/**
	 * 公司
	 */
	private String companyId;
	/**
	 * 职位名称
	 */
	private String title;
	/**
	 * 申请结束
	 */
	private java.util.Date applyEnd;
	/**
	 * 面试开始
	 */
	private java.util.Date interviewStart;
	/**
	 * 面试结束
	 */
	private java.util.Date interviewEnd;
	/**
	 * 来源1爬虫2填写
	 */
	private Integer source;

	private Long pageView;
	private Long appliedCount;
	private Integer status;
	private java.util.Date createTime;
	private java.util.Date modifyTime;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPublisherId() {		
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getPositionTag() {		
		return positionTag;
	}
	public void setPositionTag(String positionTag) {
		this.positionTag = positionTag;
	}

	public String getCityTag() {		
		return cityTag;
	}
	public void setCityTag(String cityTag) {
		this.cityTag = cityTag;
	}

	public String getExperienceTag() {		
		return experienceTag;
	}
	public void setExperienceTag(String experienceTag) {
		this.experienceTag = experienceTag;
	}

	public String getDegreeTag() {		
		return degreeTag;
	}
	public void setDegreeTag(String degreeTag) {
		this.degreeTag = degreeTag;
	}

	public String getSalaryLow() {		
		return salaryLow;
	}
	public void setSalaryLow(String salaryLow) {
		this.salaryLow = salaryLow;
	}

	public String getSalaryHigh() {		
		return salaryHigh;
	}
	public void setSalaryHigh(String salaryHigh) {
		this.salaryHigh = salaryHigh;
	}

	public String getSkillTag() {		
		return skillTag;
	}
	public void setSkillTag(String skillTag) {
		this.skillTag = skillTag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Integer getTotal() {		
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

	public java.util.Date getApplyStart() {		
		return applyStart;
	}
	public void setApplyStart(java.util.Date applyStart) {
		this.applyStart = applyStart;
	}

	public String getCompanyId() {		
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getTitle() {		
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public java.util.Date getApplyEnd() {		
		return applyEnd;
	}
	public void setApplyEnd(java.util.Date applyEnd) {
		this.applyEnd = applyEnd;
	}

	public java.util.Date getInterviewStart() {		
		return interviewStart;
	}
	public void setInterviewStart(java.util.Date interviewStart) {
		this.interviewStart = interviewStart;
	}

	public java.util.Date getInterviewEnd() {		
		return interviewEnd;
	}
	public void setInterviewEnd(java.util.Date interviewEnd) {
		this.interviewEnd = interviewEnd;
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


	public Long getPageView() {
		return pageView;
	}

	public void setPageView(Long pageView) {
		this.pageView = pageView;
	}

	public Long getAppliedCount() {
		return appliedCount;
	}

	public void setAppliedCount(Long appliedCount) {
		this.appliedCount = appliedCount;
	}
}
