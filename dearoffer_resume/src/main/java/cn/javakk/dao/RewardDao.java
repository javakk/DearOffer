package cn.javakk.dao;

import cn.javakk.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface RewardDao extends JpaRepository<Reward,String>,JpaSpecificationExecutor<Reward>{
	
}
