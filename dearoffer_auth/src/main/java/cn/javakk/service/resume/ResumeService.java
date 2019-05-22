package cn.javakk.service.resume;

import cn.javakk.dao.resume.ResumeDao;
import cn.javakk.pojo.resume.ProjectExperience;
import cn.javakk.pojo.resume.Resume;
import cn.javakk.pojo.resume.Reward;
import cn.javakk.pojo.resume.WorkExperience;
import cn.javakk.util.DateUtil;
import cn.javakk.util.IdWorker;
import cn.javakk.util.UserThreadLocal;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 服务层
 *
 * @author javakk
 */
@Service
public class ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Autowired
    private ProjectExperienceService projectExperienceService;

    @Autowired
    private RewardService rewardService;

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private IdWorker idWorker;


    /**
     * 简历查阅权限校验
     *
     * @param resumeId
     * @return
     */
    public Boolean canRead(String resumeId) {
        Long count = resumeDao.countByIdAndPublisherIdOrStatus(resumeId, UserThreadLocal.getUserId(), 2);
        return count > 0;
    }

    /**
     * 简历修改权限校验
     *
     * @param resumeId
     * @return
     */
    public Boolean canWrite(String resumeId) {
        Long count = resumeDao.countByIdAndPublisherId(resumeId, UserThreadLocal.getUserId());
        return count > 0;
    }

    /**
     * 查询我的全部列表
     *
     * @return
     */
    public List<Resume> findAll() {
        return resumeDao.findByPublisherId(UserThreadLocal.getUserId());
    }

    /**
     * 聚合简历详情
     *
     * @param id
     * @return
     */
    public Map findById(String id) {
        Map paramsMap = new HashMap(1);
        Map resultMap = new HashMap(3);
        paramsMap.put("resumeId", id);
        resultMap.put("reword", rewardService.findSearch(paramsMap));
        resultMap.put("projectExperience", projectExperienceService.findSearch(paramsMap));
        resultMap.put("workExperience", workExperienceService.findSearch(paramsMap));
        resultMap.put("resume", resumeDao.findById(id));
        return resultMap;
    }

    /**
     * 增加简历
     *
     * @param resume
     */
    public void add(Resume resume) {
        resume.setId(idWorker.nextId() + "");
        resume.setCreateTime(DateUtil.getNow());
        resume.setPublisherId(UserThreadLocal.getUserId());
        //  默认不公开
        resume.setStatus(2);
        resumeDao.save(resume);
    }

    /**
     * 修改
     *
     * @param resume
     */
    public void update(Resume resume) {
        Resume resumeVO = resumeDao.findById(resume.getId()).get();
        resume.setPublisherId(resumeVO.getPublisherId());
        resume.setCreateTime(resumeVO.getCreateTime());
        resume.setModifyTime(DateUtil.getNow());
        resume.setStatus(resumeVO.getStatus());
        resumeDao.save(resume);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        resumeDao.deleteById(id);
    }


    /**
     * 诊断
     *
     * @param id
     * @return
     */
    public Map check(String id) {
        Map queryMap = new HashMap(16);
        queryMap.put("resumeId", id);

        Map resultMap = new HashMap(16);

        List checkList = new ArrayList();
        Optional<Resume> optionalResume = resumeDao.findById(id);
        if (optionalResume.isPresent()) {
            // 简历基本信息诊断
            Resume resume = optionalResume.get();
            checkResume(resume, checkList);
            resultMap.put("resume", checkList);
            checkList.clear();

            // 项目经验
            List<ProjectExperience> projectExperiences = projectExperienceService.findSearch(queryMap);
            checkProject(projectExperiences, checkList);
            resultMap.put("project", checkList);
            checkList.clear();


            List<Reward> rewards = rewardService.findSearch(queryMap);
            checkReward(rewards, checkList);
            resultMap.put("reward", checkList);
            checkList.clear();


            List<WorkExperience> workExperiences = workExperienceService.findSearch(queryMap);
            checkWork(workExperiences, checkList);
            resultMap.put("work", checkList);
            checkList.clear();
        }

        return resultMap;
    }

    private void checkWork(List<WorkExperience> workExperiences, List checkList) {
        if (workExperiences == null || workExperiences.size() <= 1) {
            checkList.add("工作经验太少");
        } else {
            for (WorkExperience workExperience : workExperiences) {
                long duration = workExperience.getEndTime().getTime() - workExperience.getStartTime().getTime();
                if (StringUtils.isBlank(workExperience.getCompanyName())) {
                    checkList.add("工作公司名称缺失");
                } else if (StringUtils.isBlank(workExperience.getPerformance()) || workExperience.getPerformance().length() < 30) {
                    checkList.add("\"" + workExperience.getCompanyName() + "\"所获成绩太少");
                } else if (StringUtils.isBlank(workExperience.getWorkDescription()) || workExperience.getWorkDescription().length() < 30) {
                    checkList.add("\"" + workExperience.getCompanyName() + "\"工作内容不详细");
                }
                else if (duration / DateUtil.DAY < 30) {
                    checkList.add("\"" + workExperience.getCompanyName() + "\"经历周期太短");
                }
            }
        }
    }

    private void checkReward(List<Reward> rewards, List checkList) {
        if (rewards == null || rewards.size() < 3) {
            checkList.add("所获奖励太少");
        } else {
            for (Reward reward : rewards) {
                if (StringUtils.isBlank(reward.getRewardLevelTag())) {
                    checkList.add("\"" + reward.getTitle() + "\"奖励级别缺失");
                }
            }
        }
    }

    private void checkResume(Resume resume, List checkList) {
        if (StringUtils.isBlank(resume.getName())
                || StringUtils.isBlank(resume.getPhone())
                || StringUtils.isBlank(resume.getEmail())
                || resume.getAge() == null
                || StringUtils.isBlank(resume.getDegreeTag())
                || StringUtils.isBlank(resume.getExpectCity())
                || StringUtils.isBlank(resume.getExpectJob())
                || StringUtils.isBlank(resume.getDescription())
                || StringUtils.isBlank(resume.getAddress())
                || StringUtils.isBlank(resume.getPoliticalStatus())
                || resume.getSex() == null
                || StringUtils.isBlank(resume.getSchool())
                || StringUtils.isBlank(resume.getSkillTag())) {
            checkList.add("简历基本信息未完善");
        }
    }

    private void checkProject(List<ProjectExperience> projectExperiences, List checkList) {
        if (projectExperiences != null && projectExperiences.size() > 0) {
            if(projectExperiences.size() < 3) {
                checkList.add("项目经验不足");
            } else{
                for (ProjectExperience projectExperience : projectExperiences) {
                    long duration = projectExperience.getEndTime().getTime() - projectExperience.getStartTime().getTime();
                    if (duration / DateUtil.DAY < 30) {
                        checkList.add("\"" + projectExperience.getTitle() + "\"项目周期太短");
                    } else if (StringUtils.isBlank(projectExperience.getHighlight()) || projectExperience.getHighlight().length() < 10) {
                        checkList.add("\"" + projectExperience.getTitle() + "\"项目缺乏亮点");
                    } else if (StringUtils.isBlank(projectExperience.getWebsite())) {
                        checkList.add("\"" + projectExperience.getTitle() + "\"项目未上线");
                    } else if (StringUtils.isBlank(projectExperience.getJobDescrpition()) || projectExperience.getJobDescrpition().length() < 30) {
                        checkList.add("\"" + projectExperience.getTitle() + "\"项目中完成任务太少");
                    }
                }
            }
        } else {
            checkList.add("项目经验缺失");
        }
    }
}
