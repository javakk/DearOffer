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
@Table(name="resume")
public class Resume implements Serializable{

	@Id
	private String id;


	
	private String publisherId;
	/**
	 * 简历名称
	 */
	private String title;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 学校
	 */
	private String school;
	/**
	 * 学历字典
	 */
	private String degreeTag;
	/**
	 * 自我描述
	 */
	private String description;
	/**
	 * 技能字典
	 */
	private String skillTag;
	/**
	 * 期望的职位
	 */
	private String expectJob;
	/**
	 * 期望的城市
	 */
	private String expectCity;
	/**
	 * 政治面貌0 1 2
	 */
	private String politicalStatus;
	/**
	 * 附件简历
	 */
	private String attachment;
	/**
	 * 0:异常
	 * 1:公开
	 * 2:私密
	 */
	private Integer status;
	/**
	 * 性别0男1女
	 */
	private Integer sex;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 工作年限
	 */
	private Integer experienceYear;
	private java.util.Date createTime;
	private java.util.Date modifyTime;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {		
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {		
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSchool() {		
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}

	public String getDegreeTag() {		
		return degreeTag;
	}
	public void setDegreeTag(String degreeTag) {
		this.degreeTag = degreeTag;
	}

	public String getDescription() {		
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSkillTag() {		
		return skillTag;
	}
	public void setSkillTag(String skillTag) {
		this.skillTag = skillTag;
	}

	public String getExpectJob() {		
		return expectJob;
	}
	public void setExpectJob(String expectJob) {
		this.expectJob = expectJob;
	}

	public String getExpectCity() {		
		return expectCity;
	}
	public void setExpectCity(String expectCity) {
		this.expectCity = expectCity;
	}

	public String getPoliticalStatus() {		
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getAttachment() {		
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Integer getStatus() {		
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSex() {		
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {		
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {		
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(Integer experienceYear) {
		this.experienceYear = experienceYear;
	}
}
