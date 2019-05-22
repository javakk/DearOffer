package cn.javakk.dao.resume;

import cn.javakk.pojo.resume.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface WorkExperienceDao extends JpaRepository<WorkExperience,String>,JpaSpecificationExecutor<WorkExperience>{
    /**
     * 修改权限校验
     * @param experienceId
     * @param userId
     * @return
     */
    Long countByIdAndPublisherId(String experienceId, String userId);
}
