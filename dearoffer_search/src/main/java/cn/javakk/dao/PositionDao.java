package cn.javakk.dao;

import cn.javakk.pojo.Company;
import cn.javakk.pojo.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: javakk
 * @Date: 2019/4/20 20:56
 */
public interface PositionDao extends ElasticsearchRepository<Position, String> {

    /**
     * 模糊搜索职位
     * @param keyword1
     * @param keyword2
     * @param keyword3
     * @param pageable
     * @return
     */
    Page<Position> findByTitleOrDescriptionOrCompanyName(String keyword1, String keyword2, String keyword3, Pageable pageable);

}
