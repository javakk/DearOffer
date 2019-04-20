package cn.javakk.pojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * 公司搜索实体
 * @Author: javakk
 * @Date: 2019/4/20 20:39
 */
@Document(indexName = "dearoffer", type = "company")
public class Company {

    @Id
    private String id;

    /**
     * 名称
     */
    @Field(index = true, analyzer = "ik_smart_word", searchAnalyzer = "ik_smart_word")
    private String name;
    /**
     * 简称
     */
    @Field(index = true, analyzer = "ik_smart_word", searchAnalyzer = "ik_smart_word")
    private String shortName;
    /**
     * 简介
     */
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String introduction;

    /**
     * logo
     */
    private String logo;
    /**
     * 规模字典
     */
    private String companyScaleTag;
    /**
     * 融资字典
     */
    private String financingTag;
    /**
     * 综合得分
     */
    private Float score;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCompanyScaleTag() {
        return companyScaleTag;
    }

    public void setCompanyScaleTag(String companyScaleTag) {
        this.companyScaleTag = companyScaleTag;
    }

    public String getFinancingTag() {
        return financingTag;
    }

    public void setFinancingTag(String financingTag) {
        this.financingTag = financingTag;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
