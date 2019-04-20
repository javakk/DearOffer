package cn.javakk.dao;

import cn.javakk.pojo.Push;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface PushDao extends JpaRepository<Push,String>,JpaSpecificationExecutor<Push>{
	
}
