package cn.javakk.dao.resume;

import cn.javakk.pojo.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface ResumeDao extends JpaRepository<Resume,String>,JpaSpecificationExecutor<Resume>{

    /**
     * 统计符合权限的简历数量
     * @param resumeId
     * @param userId
     * @param i
     * @return
     */
    Long countByIdAndPublisherIdOrStatus(String resumeId, String userId, int i);

    /**
     * 简历修改权限
     * @param resumeId
     * @param userId
     * @return
     */
    Long countByIdAndPublisherId(String resumeId, String userId);

    /**
     * 查询我的所有简历列表
     * @param userId
     * @return
     */
    List<Resume> findByPublisherId(String userId);
}
