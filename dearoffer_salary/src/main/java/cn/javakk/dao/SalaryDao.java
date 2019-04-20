package cn.javakk.dao;

import cn.javakk.pojo.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface SalaryDao extends JpaRepository<Salary,String>,JpaSpecificationExecutor<Salary>{
	
}
