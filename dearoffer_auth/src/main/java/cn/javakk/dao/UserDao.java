package cn.javakk.dao;

import cn.javakk.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author javakk
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    /**
     *
     * @param ids
     * @return
     */
    List<User> findByIdIn(List<String> ids);
}
