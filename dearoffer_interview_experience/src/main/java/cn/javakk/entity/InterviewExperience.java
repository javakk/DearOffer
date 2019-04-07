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
@Table(name="InterviewExperience")
public class InterviewExperience implements Serializable{

	@Id
	private String id;


	/**
	 * 发布者
	 */
	private String publisherId;
	/**
	 * 经验字典
	 */
	private String experienceTag;
	/**
	 * 点赞
	 */
	private Long likeCount;
	/**
	 * 体验评分
	 */
	private Integer score;
	private Integer status;
	private String companyId;
	private String content;
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

	public String getExperienceTag() {		
		return experienceTag;
	}
	public void setExperienceTag(String experienceTag) {
		this.experienceTag = experienceTag;
	}

	public Long getLikeCount() {		
		return likeCount;
	}
	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getScore() {		
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getStatus() {		
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompanyId() {		
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getContent() {		
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
