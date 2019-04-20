package cn.javakk.service;

import cn.javakk.dao.CompanyDao;
import cn.javakk.pojo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: javakk
 * @Date: 2019/4/20 20:58
 */

@Service
public class CompanySearchService {

    @Autowired
    private CompanyDao companyDao;


    public Page<Company> search(String keyword, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Company> companies = companyDao.findByNameOrShortNameOrIntroductionLike(keyword, keyword, keyword, pageRequest);
        return companies;
    }
}
