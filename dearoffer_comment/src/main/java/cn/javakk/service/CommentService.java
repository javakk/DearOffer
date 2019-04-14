package cn.javakk.service;

import java.util.*;


import cn.javakk.entity.Comment;
import cn.javakk.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import cn.javakk.dao.CommentDao;

/**
 * 服务层
 * 
 * @author javakk
 *
 */
@Service
public class CommentService {

	private static final String COMMENT_TAG = "commentTag";
	private static final String CONTENT = "content";
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private IdWorker idWorker;

    /**
     * 通过父id查询
     * @param parentId
     * @return
     */
    public List<Comment> findByParentId(String parentId){
		return commentDao.findByParentId(parentId);
	}

	/**
	 * 分页条件查询
	 * @param companyId
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Comment> findSearch(String companyId, int page, int size) {
		Sort sort = new Sort(Sort.Direction.DESC, "createTime");
		PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
		return commentDao.findByCompanyId(companyId, pageRequest);
	}

	/**
	 * 发布新点评
	 * @param comment
	 */
	public void add(Comment comment) {
		comment.setId( idWorker.nextId()+"" );
		comment.setCreateTime(new Date());
		comment.setLikedCount(0L);
		comment.setStatus(1);
		// @Todo:用户ID未计入
		comment.setPublisherId("");
		commentDao.save(comment);
	}

	/**
	 * 点赞
	 * @param comment
	 */
	public void update(Comment comment) {
		//
		commentDao.save(comment);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		commentDao.deleteById(id);
	}

	public void updateLikedCount(String id) {
		Comment commentDO = commentDao.findById(id).get();
		commentDO.setLikedCount(commentDO.getLikedCount() + 1);
		commentDao.save(commentDO);
	}

	public Comment findById(String id) {
        Optional<Comment> option = commentDao.findById(id);
        if (option != null) {
            return option.get();
        }
        return null;
	}
}
