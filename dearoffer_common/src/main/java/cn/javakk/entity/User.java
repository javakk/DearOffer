package cn.javakk.entity;

import java.io.Serializable;

/**
 * 实体类
 * @author javakk
 *
 */
public class User implements Serializable{

	private String id;


	/**
	 * 角色id
	 */
	private String roleId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 盐
	 */
	private String salt;
	private Integer status;
	private java.util.Date createTime;
	private java.util.Date modifyTime;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {		
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {		
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {		
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {		
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {		
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeadImage() {		
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Integer getSex() {		
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSalt() {		
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
