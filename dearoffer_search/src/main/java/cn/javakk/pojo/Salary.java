package cn.javakk.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @Author: javakk
 * @Date: 2019/4/20 20:39
 */
@Document(indexName = "dearoffer", type = "salary")
public class Salary {
    @Id
    private String id;

    /**
     * 额外说明
     */
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String detail;

    /**
     * 职位名称
     */
    @Field(index = true, analyzer = "ik_smart_word", searchAnalyzer = "ik_smart_word")
    private String positionTitle;

    /**
     * 公司名字
     */
    @Field(index = true, analyzer = "ik_smart_word", searchAnalyzer = "ik_smart_word")
    private String companyName;

    /**
     * 技能标签
     */
    private String skillTag;

    /**
     * 学历字典
     */
    private String degreeTag;
    /**
     * 可信度
     */
    private Integer credibility;
    /**
     * 所属城市
     */
    private String city;
    /**
     * 月薪单位K
     */
    private Float monthTotal;
    /**
     * 期数
     */
    private Float periods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSkillTag() {
        return skillTag;
    }

    public void setSkillTag(String skillTag) {
        this.skillTag = skillTag;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDegreeTag() {
        return degreeTag;
    }

    public void setDegreeTag(String degreeTag) {
        this.degreeTag = degreeTag;
    }

    public Integer getCredibility() {
        return credibility;
    }

    public void setCredibility(Integer credibility) {
        this.credibility = credibility;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(Float monthTotal) {
        this.monthTotal = monthTotal;
    }

    public Float getPeriods() {
        return periods;
    }

    public void setPeriods(Float periods) {
        this.periods = periods;
    }
}
