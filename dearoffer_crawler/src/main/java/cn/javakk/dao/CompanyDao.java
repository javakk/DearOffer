package cn.javakk.dao;

import cn.javakk.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: JavaKK
 * @Date: 2019/4/28 14:02
 */
public interface CompanyDao extends JpaRepository<Company, String> {
    /**
     *
     * @param sourceId
     * @return
     */
    Company findBySourceId(String sourceId);
}
