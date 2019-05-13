package cn.javakk.service;

import cn.javakk.dao.AppliedInfoDao;
import cn.javakk.pojo.AppliedInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: javakk
 * @Date: 2019/4/14 15:31
 */
@Service
public class AppliedInfoService {

    private final String USER_ID = "userId";
    private final String COMPANY_ID = "companyId";
    private final String POSITION_ID = "positionId";


    @Autowired
    private AppliedInfoDao appliedInfoDao;

    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<AppliedInfo> findSearch(Map whereMap, int page, int size) {
        Specification<AppliedInfo> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return appliedInfoDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<AppliedInfo> findSearch(Map whereMap) {
        Specification<AppliedInfo> specification = createSpecification(whereMap);
        return appliedInfoDao.findAll(specification);
    }

    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<AppliedInfo> createSpecification(Map searchMap) {

        return new Specification<AppliedInfo>() {

            @Override
            public Predicate toPredicate(Root<AppliedInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 投递人
                if (searchMap.get(USER_ID)!=null && !"".equals(searchMap.get(USER_ID))) {
                    predicateList.add(cb.equal(root.get(USER_ID).as(String.class), searchMap.get(USER_ID)));
                }
                // 所属职位
                if (searchMap.get(POSITION_ID)!=null && !"".equals(searchMap.get(POSITION_ID))) {
                    predicateList.add(cb.equal(root.get(POSITION_ID).as(String.class), searchMap.get(POSITION_ID)));
                }
                // 所属公司
                if (searchMap.get(COMPANY_ID)!=null && !"".equals(searchMap.get(COMPANY_ID))) {
                    predicateList.add(cb.equal(root.get(COMPANY_ID).as(String.class), searchMap.get(COMPANY_ID)));
                }

                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
