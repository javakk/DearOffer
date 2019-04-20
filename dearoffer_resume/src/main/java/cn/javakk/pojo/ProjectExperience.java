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
@Table(name="projectExperience")
public class ProjectExperience implements Serializable{

	@Id
	private String id;


	/**
	 * 所属简历
	 */
	private String resumeId;
	/**
	 * 所属用户
	 */
	private String publisherId;
	/**
	 * 项目名称
	 */
	private String title;
	/**
	 * 所属行业
	 */
	private String industryTag;
	/**
	 * 项目亮点
	 */
	private String highlight;
	/**
	 * 技能字典
	 */
	private String skillTag;
	/**
	 * 团队规模字典
	 */
	private String teamScaleTag;
	/**
	 * 官网
	 */
	private String website;
	/**
	 * 职位名称
	 */
	private String jobTitle;
	/**
	 * 具体工作描述
	 */
	private String jobDescrpition;
	/**
	 * 开始时间
	 */
	private java.util.Date startTime;
	/**
	 * 结束时间
	 */
	private java.util.Date endTime;
	/**
	 * 所获成绩
	 */
	private String achievement;
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

	public String getTitle() {		
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getIndustryTag() {		
		return industryTag;
	}
	public void setIndustryTag(String industryTag) {
		this.industryTag = industryTag;
	}

	public String getHighlight() {		
		return highlight;
	}
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getSkillTag() {		
		return skillTag;
	}
	public void setSkillTag(String skillTag) {
		this.skillTag = skillTag;
	}

	public String getTeamScaleTag() {		
		return teamScaleTag;
	}
	public void setTeamScaleTag(String teamScaleTag) {
		this.teamScaleTag = teamScaleTag;
	}

	public String getWebsite() {		
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}

	public String getJobTitle() {		
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescrpition() {		
		return jobDescrpition;
	}
	public void setJobDescrpition(String jobDescrpition) {
		this.jobDescrpition = jobDescrpition;
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

	public String getAchievement() {		
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
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
