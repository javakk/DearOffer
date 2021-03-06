package cn.javakk.dao.base;

import cn.javakk.pojo.base.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author: javakk
 * @Date: 2019/3/23 17:52
 */

@Repository
public interface DictionaryDao extends JpaRepository<Dictionary, String>, JpaSpecificationExecutor<Dictionary> {

}
