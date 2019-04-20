package cn.javakk.pojo;

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
@Table(name="workExperience")
public class WorkExperience implements Serializable{

	@Id
	private String id;


	/**
	 * 所属简历
	 */
	private String resumeId;
	/**
	 * 发布者
	 */
	private String publisherId;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 技能字典
	 */
	private String skillTag;
	/**
	 * 开始日期
	 */
	private java.util.Date startTime;
	/**
	 * 结束日期
	 */
	private java.util.Date endTime;
	/**
	 * 职位名称
	 */
	private String positionTitle;
	/**
	 * 工作描述
	 */
	private String workDescription;
	/**
	 * 公司类型字典
	 */
	private String companyTypeTag;
	/**
	 * 个人表现
	 */
	private String performance;
	/**
	 * 公司规模字典
	 */
	private String companyScaleTag;
	/**
	 * 所属行业字典
	 */
	private String industryTag;
	private Integer status;
	private java.util.Date createTime;
	private java.util.Date modifyTime;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getResumeId() {		
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}

	public String getPublisherId() {		
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getCompanyName() {		
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSkillTag() {		
		return skillTag;
	}
	public void setSkillTag(String skillTag) {
		this.skillTag = skillTag;
	}

	public java.util.Date getStartTime() {		
		return startTime;
	}
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {		
		return endTime;
	}
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public String getPositionTitle() {		
		return positionTitle;
	}
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public String getCompanyTypeTag() {		
		return companyTypeTag;
	}
	public void setCompanyTypeTag(String companyTypeTag) {
		this.companyTypeTag = companyTypeTag;
	}

	public String getPerformance() {		
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
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

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}
}
