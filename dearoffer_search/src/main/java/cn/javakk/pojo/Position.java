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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
