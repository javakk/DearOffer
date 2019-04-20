package cn.javakk.dao;

import cn.javakk.entity.ProjectExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface ProjectExperienceDao extends JpaRepository<ProjectExperience,String>,JpaSpecificationExecutor<ProjectExperience>{
    /**
     * 修改权限校验
     * @param projectId
     * @param userId
     * @return
     */
    Long countByIdAndPublisherId(String projectId, String userId);
}
