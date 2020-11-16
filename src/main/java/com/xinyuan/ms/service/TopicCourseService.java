package com.xinyuan.ms.service;

import com.xinyuan.ms.common.service.ParamCondition;
import com.xinyuan.ms.common.service.SelectParam;
import com.xinyuan.ms.entity.TopicCourse;
import com.xinyuan.ms.mapper.TopicCourseDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicCourseService extends BaseService<TopicCourseDao, TopicCourse,Long> {

    /**
     * 保存题目课程关系
     * @param topicCourse
     */
    public void saveTopicCourse(TopicCourse topicCourse){
        save(topicCourse);
    }

    /**
     * 根据题目id查找所属的课程id
     * @param tId
     */
    public List<Long> findCourseIdByTopicId(Long tId){
        List<SelectParam> selectParams = new ArrayList<>();
        SelectParam selectParam = new SelectParam("tId",tId, ParamCondition.EQUAL);
        selectParams.add(selectParam);
        List<TopicCourse> topicCourses = findByCondition(selectParams);
        List<Long> ids = new ArrayList<>();
        for (TopicCourse topicCourse : topicCourses){
            ids.add(topicCourse.getCId());
        }
        return ids;
    }

}
