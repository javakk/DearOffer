package cn.javakk.base.dao;

import cn.javakk.base.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: javakk
 * @Date: 2019/3/23 17:52
 */

@Repository
public interface DictionaryDao extends JpaRepository<Dictionary, String>, JpaSpecificationExecutor<Dictionary> {

    /**
     * 通过tag查询item
     * @param tag
     * @return
     */
    List<Dictionary> findByTagOrderBySortDesc(String tag);

    /**
     * 通过code唯一检索dic
     * @param code
     * @return
     */
    Dictionary findByCode(String code);
}
