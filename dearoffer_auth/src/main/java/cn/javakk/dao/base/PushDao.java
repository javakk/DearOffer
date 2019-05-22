package cn.javakk.dao.base;

import cn.javakk.pojo.base.Push;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * 数据访问接口
 * @author JavaKK
 *
 */
public interface PushDao extends JpaRepository<Push,String>,JpaSpecificationExecutor<Push>{

    /**
     * 查询今日数据
     * @param start
     * @param end
     * @return
     */
    @Query(value = "select * from push where startTime <= ?1 AND endTime >= ?2", nativeQuery = true)
    List<Push> findTodayPush(Date start, Date end);
}
