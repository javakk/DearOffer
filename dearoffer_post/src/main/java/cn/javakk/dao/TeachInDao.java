package cn.javakk.dao;

import cn.javakk.pojo.TeachIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface TeachInDao extends JpaRepository<TeachIn,String>,JpaSpecificationExecutor<TeachIn>{

    /**
     * 符合日期的宣讲会查询
     * @param today
     * @param endDate
     * @return
     */
    @Query(value = "select * from teachIn where startDate between ?1 and ?2", nativeQuery = true)
    List<TeachIn> findByStartDateBetween(Date today, Date endDate);
}
