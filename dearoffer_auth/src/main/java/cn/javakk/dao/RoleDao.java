package cn.javakk.dao;

import cn.javakk.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface RoleDao extends JpaRepository<Role,String>,JpaSpecificationExecutor<Role>{
	
}
