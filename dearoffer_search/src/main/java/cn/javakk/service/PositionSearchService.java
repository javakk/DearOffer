package cn.javakk.service;

import cn.javakk.dao.InterviewExperienceDao;
import cn.javakk.dao.PositionDao;
import cn.javakk.pojo.InterviewExperience;
import cn.javakk.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: javakk
 * @Date: 2019/4/20 20:58
 */

@Service
public class PositionSearchService {

    @Autowired
    private PositionDao positionDao;

    public Page<Position> search(String keyword, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Position> positions = positionDao.findByTitleOrDescriptionOrCompanyName(keyword, keyword, keyword, pageRequest);
        return positions;
    }
}
