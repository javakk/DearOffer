package cn.javakk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="position")
public class Position implements Serializable{

	@Id
	private String id;//


	
	private String publisherId;//发布者
	private String positionTag;//职位类型字典
	private String cityTag;//所在城市字典
	private String experienceTag;//工作时间字典
	private String degreeTag;//学历字典
	private String address;//
	private String industry;//
	private String email;//
	private String description;//具体描述
	private String attachment;//附件说明
	private String introduction;//
	private String kind;//公司类别
	private String companyName;//公司name
	private String companyId;//公司id
	private String title;//职位名称
	private String phone;//
	private String scale;
	private String way;//
	private String source;//来源1爬虫2填写
	private Long pageView;//点击量
	private Integer status;//
	private java.util.Date createTime;//
	private java.util.Date modifyTime;//

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
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

	public String getAddress() {		
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getIndustry() {		
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getEmail() {		
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	public String getIntroduction() {		
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getKind() {		
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
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

	public String getTitle() {		
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWay() {		
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}

	public String getSource() {		
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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


	@Override
	public String toString() {
		return "Position{" +
				"id='" + id + '\'' +
				", publisherId='" + publisherId + '\'' +
				", positionTag='" + positionTag + '\'' +
				", cityTag='" + cityTag + '\'' +
				", experienceTag='" + experienceTag + '\'' +
				", degreeTag='" + degreeTag + '\'' +
				", address='" + address + '\'' +
				", industry='" + industry + '\'' +
				", email='" + email + '\'' +
				", description='" + description + '\'' +
				", attachment='" + attachment + '\'' +
				", introduction='" + introduction + '\'' +
				", kind='" + kind + '\'' +
				", companyName='" + companyName + '\'' +
				", companyId='" + companyId + '\'' +
				", title='" + title + '\'' +
				", phone='" + phone + '\'' +
				", scale='" + scale + '\'' +
				", way='" + way + '\'' +
				", source='" + source + '\'' +
				", pageView=" + pageView +
				", status=" + status +
				", createTime=" + createTime +
				", modifyTime=" + modifyTime +
				'}';
	}
}
