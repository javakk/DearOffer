package cn.javakk.dao;

import cn.javakk.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface WorkExperienceDao extends JpaRepository<WorkExperience,String>,JpaSpecificationExecutor<WorkExperience>{
	
}
