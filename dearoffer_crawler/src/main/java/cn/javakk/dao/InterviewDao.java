package cn.javakk.dao;

import cn.javakk.entity.InterviewExperience;
import cn.javakk.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 数据访问接口
 * @author javakk
 *
 */
@Repository
public interface InterviewDao extends JpaRepository<InterviewExperience,String>,JpaSpecificationExecutor<InterviewExperience>{
	
}
