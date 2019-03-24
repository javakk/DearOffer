package cn.javakk.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.javakk.base.dao.DictionaryDao;
import cn.javakk.base.entity.Dictionary;
import cn.javakk.base.entity.DictionaryField;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/**
 * @Author: javakk
 * @Date: 2019/3/23 17:51
 */

@Service
public class DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     * @return
     */
    public List<Dictionary> findAll() {
        return dictionaryDao.findAll();
    }


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
     * 增加
     * @param dictionary
     */
    public void add(Dictionary dictionary) {
        dictionary.setId( idWorker.nextId()+"" );
        dictionaryDao.save(dictionary);
    }

    /**
     * 修改
     * @param dictionary
     */
    public void update(Dictionary dictionary) {
        dictionaryDao.save(dictionary);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id) {
        dictionaryDao.deleteById(id);
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
                if (searchMap.get(DictionaryField.ID)!=null && !"".equals(searchMap.get(DictionaryField.ID))) {
                    predicateList.add(cb.equal(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 父id
                if (searchMap.get(DictionaryField.PARENT_ID)!=null && !"".equals(searchMap.get(DictionaryField.PARENT_ID))) {
                    predicateList.add(cb.equal(root.get("parent_id").as(String.class), "%"+(String)searchMap.get("parent_id")+"%"));
                }
                // 来源tag
                if (searchMap.get(DictionaryField.TAG)!=null && !"".equals(searchMap.get(DictionaryField.TAG))) {
                    predicateList.add(cb.equal(root.get("tag").as(String.class), "%"+(String)searchMap.get("tag")+"%"));
                }
                // 字典代码
                if (searchMap.get(DictionaryField.CODE)!=null && !"".equals(searchMap.get(DictionaryField.CODE))) {
                    predicateList.add(cb.equal(root.get("code").as(String.class), "%"+(String)searchMap.get("code")+"%"));
                }

                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
    
}
