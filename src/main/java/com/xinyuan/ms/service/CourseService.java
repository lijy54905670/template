package com.xinyuan.ms.service;

import com.xinyuan.ms.entity.Course;
import com.xinyuan.ms.mapper.CourseDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService extends BaseService<CourseDao, Course,Long> {


    /**
     * 根据课程id查找课程名
     * @param ids
     * @return
     */
    public List<String> findCourseNameById(List<Long> ids){
        List<String> names = new ArrayList<>();
        for (Long id : ids){
            Course course = get(id);
            names.add(course.getName());
        }
        return names;
    }

}
