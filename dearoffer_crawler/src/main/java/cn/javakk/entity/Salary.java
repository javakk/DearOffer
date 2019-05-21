package cn.javakk.entity;

import cn.javakk.crawler.util.DateUtil;

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
@Table(name="salary")
public class Salary implements Serializable{

	@Id
	private String id;

	/**
	 * 所属公司
	 */
	private String companyName;
	/**
	 * 发布者
	 */
	private String publisherId;
	/**
	 * 月薪单位K
	 */
	private Float monthTotal;
	/**
	 * 期数
	 */
	private Float periods;
	/**
	 * 额外说明
	 */
	private String detail;
	/**
	 * 可信度
	 */
	private Integer credibility;
	/**
	 * 学历字典
	 */
	private String degreeTag;
	/**
	 * 其他福利
	 */
	private String bonus;
	/**
	 * 所属城市
	 */
	private String city;
	/**
	 * 职位名称
	 */
	private String positionTitle;
	/**
	 * 技能标签
	 */
	private String skillTag;
	/**
	 * 浏览量
	 */
	private Long pageView;
	private Integer status;
	private java.util.Date createTime;
	private java.util.Date modifyTime;

	@Transient
	private Float credibilityPercent;

	@Transient
	private String timePassed;

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

	public String getPublisherId() {		
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public Float getMonthTotal() {		
		return monthTotal;
	}
	public void setMonthTotal(Float monthTotal) {
		this.monthTotal = monthTotal;
	}

	public Float getPeriods() {		
		return periods;
	}
	public void setPeriods(Float periods) {
		this.periods = periods;
	}

	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getCredibility() {
		return credibility;
	}
	public void setCredibility(Integer credibility) {
		this.credibility = credibility;
	}

	public String getDegreeTag() {		
		return degreeTag;
	}
	public void setDegreeTag(String degreeTag) {
		this.degreeTag = degreeTag;
	}

	public String getBonus() {		
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getCity() {		
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getPositionTitle() {		
		return positionTitle;
	}
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public String getSkillTag() {		
		return skillTag;
	}
	public void setSkillTag(String skillTag) {
		this.skillTag = skillTag;
	}

	public Long getPageView() {		
		return pageView;
	}
	public void setPageView(Long pageView) {
		this.pageView = pageView;
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


	public Float getCredibilityPercent() {
		return credibilityPercent;
	}

	public void setCredibilityPercent(Float credibilityPercent) {
		this.credibilityPercent = credibilityPercent;
	}

	public String getTimePassed() {
		return DateUtil.passedString(this.createTime);
	}
}
