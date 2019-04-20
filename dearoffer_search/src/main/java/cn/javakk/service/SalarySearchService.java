package cn.javakk.service;

import cn.javakk.dao.PositionDao;
import cn.javakk.dao.SalaryDao;
import cn.javakk.pojo.Position;
import cn.javakk.pojo.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: javakk
 * @Date: 2019/4/20 20:58
 */

@Service
public class SalarySearchService {

    @Autowired
    private SalaryDao salaryDao;


    public Page<Salary> search(String keyword, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Salary> salaries = salaryDao.findByCompanyNameOrPositionTitleOrDetailLike(keyword, keyword, keyword, pageRequest);
        return salaries;
    }
}
