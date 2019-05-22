package cn.javakk.pojo.resume;

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
@Table(name="reward")
public class Reward implements Serializable{

	@Id
	private String id;


	/**
	 * 所属简历
	 */
	private String resumeId;
	/**
	 * 所属人
	 */
	private String publisherId;
	/**
	 * 奖项名称
	 */
	private String title;
	/**
	 * 奖项类别字典
	 */
	private String rewardLevelTag;
	/**
	 * 颁发日期
	 */
	private java.util.Date issuedTime;
	/**
	 * 获奖等级
	 */
	private String result;
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

	public String getRewardLevelTag() {		
		return rewardLevelTag;
	}
	public void setRewardLevelTag(String rewardLevelTag) {
		this.rewardLevelTag = rewardLevelTag;
	}

	public java.util.Date getIssuedTime() {		
		return issuedTime;
	}
	public void setIssuedTime(java.util.Date issuedTime) {
		this.issuedTime = issuedTime;
	}

	public String getResult() {		
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
