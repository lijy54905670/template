package com.xinyuan.ms.service;

import com.xinyuan.ExcelTest.ExportExcel;
import com.xinyuan.ms.common.util.ExportExcelUtil;
import com.xinyuan.ms.common.util.ExportExcelWrapper;
import com.xinyuan.ms.common.util.PageUtil;
import com.xinyuan.ms.common.web.Conditions;
import com.xinyuan.ms.common.web.PageBody;
import com.xinyuan.ms.entity.TOption;
import com.xinyuan.ms.entity.Topic;
import com.xinyuan.ms.entity.TopicCourse;
import com.xinyuan.ms.mapper.TopicDao;
import com.xinyuan.ms.web.request.SaveIdsState;
import com.xinyuan.ms.web.request.SaveTopicOptions;
import com.xinyuan.ms.web.vo.TopicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService extends BaseService<TopicDao, Topic,Long> {

    @Autowired
    ClassifyService classifyService;

    @Autowired
    TOptionService tOptionService;

    @Autowired
    TopicCourseService topicCourseService;

    @Autowired
    CourseService courseService;

    @Autowired
    QuestionTypeService questionTypeService;

    @Autowired
    StatusService statusService;


    /**
     * 保存题目选项
     * @param saveTopicOptions
     */
    public void saveTopicOptions(SaveTopicOptions saveTopicOptions){
        Topic topic = saveTopicOptions.getTopic();                  //获取题目
        List<TOption> tOptions = saveTopicOptions.getTOptionList(); //获取选项
        List<Long> courseIds = saveTopicOptions.getCourseIds();  //获取题目所属课程id
        Long id = topic.getId();

        if (id == null || id == 0){                 //新增的题目

            Topic save = save(topic);               //保存题目，返回保存后的记录

            for (Long courseId : courseIds) {      //保存题目所属课程(一个问题可能对应多个选项)
                TopicCourse topicCourse = new TopicCourse();
                topicCourse.setTId(save.getId());
                topicCourse.setCId(courseId);
                topicCourseService.saveTopicCourse(topicCourse);         //保存题目所属课程
            }

            if (save.getQuestionTypeId() == 1){                //如果题目是选项题，则继续保存选项
                Long topicId = save.getId();
                tOptionService.saveOptions(tOptions,topicId);
            }
        }else {
            update(topic);
            if (topic.getQuestionTypeId() == 1){   //如果是选择题

            }
        }

    }


    /**
     * 修改题目状态
     * @param saveIdsState
     */
    public void modify(SaveIdsState saveIdsState){
        List<Long> ids = saveIdsState.getIds();       //得到需要修改的题目id
        int state1 = saveIdsState.getState();          //得到执行操作（0删除，1启用，2停用）

            for (Long id : ids){
                Topic result = get(id);
                if (result.getStatus() != 1){    //如果勾选的题目不是已经删除的，则执行操作
                    Topic topic = new Topic();
                    topic.setId(id);
                    topic.setStatus(state1);
                    update(topic);             //更新题目状态
                }
            }
    }

    /**
     * 得到查询题目显示到页面上的内容
     * @param pageBody
     */
    public Page queryTopic(PageBody pageBody){

        Page page = query(pageBody);
        List<TopicVo> topicVos = getTopicVo(page);

        return toPage(page,topicVos);
    }

    /**
     * 取出page中的content，对其中的id修改为对应的名字
     * @param page
     * @return
     */
    public List<TopicVo> getTopicVo(Page page){
        List<Topic> topics = page.getContent();
        List<TopicVo> topicVos = new ArrayList<>();
        for (Topic topic : topics){                    //遍历查询得到的Topic,将其中的外键变为外键对应的名字
            TopicVo topicVo = topicToTopicVO(topic);   //将topic对象变为topicVo对象
            topicVos.add(topicVo);
        }
        return topicVos;
    }

    /**
     * 导出
     * @param pageBody
     * @throws FileNotFoundException
     */
    public void export(PageBody pageBody) throws FileNotFoundException {
        Page page = query(pageBody);
        List<TopicVo> topicVos = getTopicVo(page);           //得到需要导出的内容
        ExportExcelUtil<TopicVo> util = new ExportExcelUtil<>(); //创建导出工具类对象

        List<TopicVo> list = new ArrayList<>();              //将要导出的数据放入list中
        for (TopicVo topicVo : topicVos){
            list.add(topicVo);
        }
        String[] columnNames = { "ID", "题干", "所属分类","所属课程","题型","难易程度","状态" };  //设置表头

        util.exportExcel("用户导出", columnNames, list, new FileOutputStream("C:\\Users\\yaoli\\Desktop\\test1.xls"), ExportExcelUtil.EXCEL_FILE_2003);
    }



    public void export2(HttpServletResponse response) throws FileNotFoundException {
        PageBody pageBody = new PageBody();
        ArrayList<Conditions> condition = new ArrayList<>();
        Conditions conditions = new Conditions();
        conditions.setCondition("EQUAL");
        conditions.setValue(3);
        conditions.setKey("classifyId");
        condition.add(conditions);
        pageBody.setConditions(condition);
        Page page = query(pageBody);
        List<TopicVo> topicVos = getTopicVo(page);           //得到需要导出的内容

        List<TopicVo> list = new ArrayList<>();              //将要导出的数据放入list中
        for (TopicVo topicVo : topicVos){
            list.add(topicVo);
        }
        String[] columnNames = { "ID", "题干", "所属分类","所属课程","题型","难易程度","状态" };  //设置表头
        ExportExcelWrapper<TopicVo> util = new ExportExcelWrapper<TopicVo>();
        String fileName = "excel1";
        util.exportExcel(fileName, fileName, columnNames, list, response, ExportExcelUtil.EXCEL_FILE_2003);

    }




    /**
     * 将得到的
     * @param page
     * @param
     * @return
     */
    public Page toPage(Page page,List<TopicVo> topicVos){
        PageUtil pageUtil = new PageUtil(page.getNumber(),page.getSize(),topicVos);
        return pageUtil;
    }

    /**
     * 将topic变成需要的topicVO对象
     * @param topic
     */
    public TopicVo topicToTopicVO(Topic topic){
        TopicVo topicVo = new TopicVo();  //新建一个用于返回到页面的vo对象

        topicVo.setClassifyName(classifyService.findClassifyNameById(topic.getClassifyId()));  //设置分类id对应的分类名
        topicVo.setId(topic.getId());
        topicVo.setComplexity(topic.getComplexity());
        topicVo.setQuestionStem(topic.getQuestionStem());
        topicVo.setCourseName(courseService.findCourseNameById(topicCourseService.findCourseIdByTopicId(topic.getId())));
        topicVo.setQuestionTypeName(questionTypeService.findTypeById(topic.getQuestionTypeId()));
        topicVo.setStatus(statusService.findNameById(topic.getStatus()));
        return topicVo;
    }
}
