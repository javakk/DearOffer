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
@Table(name="comment")
public class Comment implements Serializable{

	@Id
	private String id;


	
	private String publisherId;
	/**
	 * 得分（满分10）
	 */
	private Integer score;
	/**
	 * 标签
	 */
	private String commentTag;
	private Long likedCount;
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

	public Integer getScore() {		
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

	public String getCommentTag() {		
		return commentTag;
	}
	public void setCommentTag(String commentTag) {
		this.commentTag = commentTag;
	}

	public Long getLikedCount() {		
		return likedCount;
	}
	public void setLikedCount(Long likedCount) {
		this.likedCount = likedCount;
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
	public void setCompanyId(String compnayId) {
		this.companyId = compnayId;
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
