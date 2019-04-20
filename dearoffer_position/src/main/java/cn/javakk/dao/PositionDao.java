package cn.javakk.dao;

import cn.javakk.pojo.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface PositionDao extends JpaRepository<Position,String>,JpaSpecificationExecutor<Position>{
	
}
