package cn.javakk.entity;

import java.util.List;

/**
 * 分页结果集
 * @Author: javakk
 * @Date: 2019/3/23 16:32
 */
public class PageResult<T> {
    /**
     * 总数
     */
    private Long total;
    /**
     * 列表
     */
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
