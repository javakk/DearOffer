package cn.javakk.dao;

import cn.javakk.pojo.Position;
import cn.javakk.pojo.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: javakk
 * @Date: 2019/4/20 20:56
 */
public interface SalaryDao extends ElasticsearchRepository<Salary, String> {


    /**
     * 薪资模糊搜索
     * @param keyword1
     * @param keyword2
     * @param keyword3
     * @param pageable
     * @return
     */
    Page<Salary> findByCompanyNameOrPositionTitleOrDetailLike(String keyword1,
                                                              String keyword2,
                                                              String keyword3,
                                                              Pageable pageable);
}
