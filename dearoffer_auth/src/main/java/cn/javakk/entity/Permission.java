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
@Table(name="permission")
public class Permission implements Serializable{

	@Id
	private String id;


	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 具体描述
	 */
	private String descrption;
	/**
	 * Url : //***//***
	 */
	private String target;
	private Integer status;
	private java.util.Date createTime;
	private java.util.Date modifyTime;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescrption() {		
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public String getTarget() {		
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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
