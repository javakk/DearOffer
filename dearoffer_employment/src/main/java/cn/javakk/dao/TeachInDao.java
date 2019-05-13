package cn.javakk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.javakk.pojo.TeachIn;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface TeachInDao extends JpaRepository<TeachIn,String>,JpaSpecificationExecutor<TeachIn>{
	
}
