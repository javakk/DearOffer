package cn.javakk.dao;

import cn.javakk.pojo.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: javakk
 * @Date: 2019/4/20 20:56
 */
public interface CompanyDao extends ElasticsearchRepository<Company, String> {
    /**
     * 模糊搜索公司
     * @param keyword
     * @param keyword1
     * @param keyword2
     * @param pageable
     * @return
     */
    Page<Company> findByNameOrShortNameOrIntroductionLike(String keyword, String keyword1, String keyword2, Pageable pageable);
}
