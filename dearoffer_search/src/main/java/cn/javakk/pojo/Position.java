package cn.javakk.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

/**
 * @Author: javakk
 * @Date: 2019/4/20 20:44
 */
@Document(indexName = "dearoffer", type = "position")
public class Position {
    @Id
    private String id;

    /**
     * 具体描述
     */
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String description;

    /**
     * 职位名称
     */
    @Field(index = true, analyzer = "ik_smart_word", searchAnalyzer = "ik_smart_word")
    private String title;

    @Field(index = true, analyzer = "ik_smart_word", searchAnalyzer = "ik_smart_word")
    private String companyName;

    @Field(index = true, analyzer = "ik_smart_word", searchAnalyzer = "ik_smart_word")
    private String introduction;



    private String degreeTag;
    private String cityTag;
    private String kind;
    private String scale;
    private String way;
    private String pageView;
    private String industry;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDegreeTag() {
        return degreeTag;
    }

    public void setDegreeTag(String degreeTag) {
        this.degreeTag = degreeTag;
    }

    public String getCityTag() {
        return cityTag;
    }

    public void setCityTag(String cityTag) {
        this.cityTag = cityTag;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getPageView() {
        return pageView;
    }

    public void setPageView(String pageView) {
        this.pageView = pageView;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
