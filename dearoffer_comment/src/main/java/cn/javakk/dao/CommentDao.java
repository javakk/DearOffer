package cn.javakk.dao;

import cn.javakk.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface CommentDao extends JpaRepository<Comment,String>,JpaSpecificationExecutor<Comment>{
	
}
