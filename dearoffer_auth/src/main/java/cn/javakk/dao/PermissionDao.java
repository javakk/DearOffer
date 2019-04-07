package cn.javakk.dao;

import cn.javakk.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface PermissionDao extends JpaRepository<Permission,String>,JpaSpecificationExecutor<Permission>{
	
}
