package cn.javakk.dao;

import cn.javakk.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface ResumeDao extends JpaRepository<Resume,String>,JpaSpecificationExecutor<Resume>{
	
}
