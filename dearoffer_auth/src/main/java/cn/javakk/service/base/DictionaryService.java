package cn.javakk.service.base;


import cn.javakk.dao.base.DictionaryDao;
import cn.javakk.pojo.base.Dictionary;
import cn.javakk.util.IdWorker;
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
 * @Date: 2019/3/23 17:51
 */

@Service
public class DictionaryService {

    private static final String PARENT_ID = "parent_id";
    private static final String ID = "id";
    private static final String TAG = "tag";
    private static final String CODE = "code";
    @Autowired
    private DictionaryDao dictionaryDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Dictionary> findSearch(Map whereMap, int page, int size) {
        Specification<Dictionary> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return dictionaryDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<Dictionary> findSearch(Map whereMap) {
        Specification<Dictionary> specification = createSpecification(whereMap);
        return dictionaryDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public Dictionary findById(String id) {
        return dictionaryDao.findById(id).get();
    }

    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<Dictionary> createSpecification(Map searchMap) {

        return new Specification<Dictionary>() {

            @Override
            public Predicate toPredicate(Root<Dictionary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // id
                if (searchMap.get(ID)!=null && !"".equals(searchMap.get(ID))) {
                    predicateList.add(cb.equal(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 父id
                if (searchMap.get(PARENT_ID)!=null && !"".equals(searchMap.get(PARENT_ID))) {
                    predicateList.add(cb.equal(root.get("parent_id").as(String.class), "%"+(String)searchMap.get("parent_id")+"%"));
                }
                // 来源tag
                if (searchMap.get(TAG)!=null && !"".equals(searchMap.get(TAG))) {
                    predicateList.add(cb.equal(root.get("tag").as(String.class), "%"+(String)searchMap.get("tag")+"%"));
                }
                // 字典代码
                if (searchMap.get(CODE)!=null && !"".equals(searchMap.get(CODE))) {
                    predicateList.add(cb.equal(root.get("code").as(String.class), "%"+(String)searchMap.get("code")+"%"));
                }

                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
    
}
