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
@Table(name="rolePermission")
public class RolePermission implements Serializable{

	@Id
	private String id;


	/**
	 * 角色id
	 */
	private String roleId;
	/**
	 * 权限id
	 */
	private String permissionId;
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

	public String getPermissionId() {		
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
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
