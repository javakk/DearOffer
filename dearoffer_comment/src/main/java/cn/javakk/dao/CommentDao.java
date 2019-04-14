package cn.javakk.dao;

import cn.javakk.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据访问接口
 * @author javakk
 *
 */
@Repository
public interface CommentDao extends MongoRepository<Comment, String> {

    /**
     * 通过父id分页查询点评
     * @param parentId
     * @return
     */
    List<Comment> findByParentId(String parentId);

    /**
     * 通过companyId查询所有
     * @param companyId
     * @param pageRequest
     * @return
     */
    Page<Comment> findByCompanyId(String companyId, Pageable pageRequest);
}
