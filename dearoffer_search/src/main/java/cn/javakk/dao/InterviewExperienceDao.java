package cn.javakk.dao;

import cn.javakk.pojo.InterviewExperience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: javakk
 * @Date: 2019/4/20 22:23
 */
public interface InterviewExperienceDao extends ElasticsearchRepository<InterviewExperience, String> {

    /**
     * 搜索面经
     *
     * @param keyword1
     * @param keyword2
     * @param keyword3
     * @param pageable
     * @return
     */
    Page<InterviewExperience> findByContentOrCompanyNameOrTitleLike(String keyword1, String keyword2, String keyword3, Pageable pageable);
}
