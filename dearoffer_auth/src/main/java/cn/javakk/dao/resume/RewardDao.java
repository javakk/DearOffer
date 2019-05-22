package cn.javakk.dao.resume;

import cn.javakk.pojo.resume.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface RewardDao extends JpaRepository<Reward,String>,JpaSpecificationExecutor<Reward>{

    /**
     * 修改权限校验
     * @param rewardId
     * @param userId
     * @return
     */
    Long countByIdAndPublisherId(String rewardId, String userId);
}
