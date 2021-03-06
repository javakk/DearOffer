package cn.javakk.dao;

import cn.javakk.pojo.InterviewExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface InterviewExperienceDao extends JpaRepository<InterviewExperience,String>,JpaSpecificationExecutor<InterviewExperience>{
	
}
