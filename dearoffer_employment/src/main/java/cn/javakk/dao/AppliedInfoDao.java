package cn.javakk.dao;

import cn.javakk.pojo.AppliedInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: javakk
 * @Date: 2019/4/14 15:37
 */
public interface AppliedInfoDao extends JpaRepository<AppliedInfo,String>,JpaSpecificationExecutor<AppliedInfo> {

}
