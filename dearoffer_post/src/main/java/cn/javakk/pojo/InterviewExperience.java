package cn.javakk.pojo;

import cn.javakk.util.DateUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 实体类
 * @author javakk
 *
 */
@Entity
@Table(name="interviewExperience")
public class InterviewExperience implements Serializable{


	@Id
	private String id;//


	private String title;
	private String publisherId;//发布者
	private String publisherName;//
	private String publisherHead;//
	private String publisherDate;//
	private Long likeCount;//点赞
	private Integer score;//体验评分
	private Integer status;//
	private String companyName;//公司名称
	private String companyId;//公司
	private String content;//内容
	private String source;//
	@Transient
	private String timePassed;
	private java.util.Date createTime;//
	private java.util.Date modifyTime;//
	public String getTimePassed() {
		return DateUtil.passedString(this.createTime);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherHead() {
		return publisherHead;
	}
	public void setPublisherHead(String publisherHead) {
		this.publisherHead = publisherHead;
	}

	public String getPublisherDate() {
		return publisherDate;
	}
	public void setPublisherDate(String publisherDate) {
		this.publisherDate = publisherDate;
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

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
