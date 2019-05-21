package cn.javakk.crawler.pipeline;

import cn.javakk.dao.CompanyDao;
import cn.javakk.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Author: JavaKK
 * @Date: 2019/4/8 9:37
 */

@Service
public class CompanyPipeline implements Pipeline {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public void process(ResultItems resultItems, Task task) {

        Company company = resultItems.get("company");

        if (company == null) {
            return;
        }

        Company exsitsCompany = companyDao.findBySourceId(company.getSourceId());
        if (exsitsCompany == null) {
            companyDao.save(company);
        }



//        Map<String, Object> nodes = resultItems.getAll();
//        for (Map.Entry<String, Object> node : nodes.entrySet()) {
//            if (node.getValue() != null) {
//                System.out.println(StringEscapeUtils.unescapeJson(node.getValue().toString()));
//            }
//        }
    }


}
