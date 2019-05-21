package cn.javakk.dao;

import cn.javakk.pojo.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface CompanyDao extends JpaRepository<Company,String>,JpaSpecificationExecutor<Company>{
	
}
