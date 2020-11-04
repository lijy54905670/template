package com.xinyuan.ms.service;

import com.xinyuan.ms.common.util.EntityUtils;
import com.xinyuan.ms.common.util.ReflectionUtils;
import com.xinyuan.ms.entity.Question;
import com.xinyuan.ms.mapper.QuestionDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QuestionService{

    @Autowired
    QuestionDao questionDao;

    /**
     * 保存问题（新增，删除，修改）
     * @param question
     */
    public Long save(Question question){
        Long id = question.getId();             //获取题目id,如果为空则表示新增
        int deleted = question.getDeleted();    //获取删除标志
        Long resultId = null;                   //用于返回，如果不为空，则表示是新增的问题
        if (id == null){
            resultId = add(question);               //得到插入的记录的id，用于选项插入
        }else if (deleted == 1){
                delete(id);                         //删除问题
        }else {
            update(question);                       //更新问题
        }
        return resultId;
    }

    /**
     *  新增问题
     * @param question
     * @return
     */
    public Long add(Question question){
        Question result = questionDao.saveAndFlush(question);
        return result.getId();
    }

    /**
     * 删除问题（通过id）
     * @param id
     */
    public void delete(Long id){
        Question entity = questionDao.findOne(id);
        if (entity != null) {
            if (ReflectionUtils.hasField(entity, "deleted")) {
                ReflectionUtils.invokeSetter(entity, "deleted", 1);
            }
            Question result = questionDao.save(entity);
            log.info("删除id为："+ entity.getId() + " 标题为：" + entity.getTitle() +"的问题!");
        }
    }

    /**
     * 更新问题
     * @param question  问题对象
     */
    public void update(Question question){
        Question result = null;
        if (ReflectionUtils.hasField(question, "id")) {                       //判断传入的User对象是否存在id属性
            Long qId = (Long) ReflectionUtils.getFieldValue(question, "id");       //获取id的属性值（没有设置的话为null）
            result = questionDao.findOne(question.getId());                                      //通过得到的值，从数据库中查询对应的记录（如果你save没有给id话，回报错）
        }
        EntityUtils.copyPropertiesIgnoreNull(question, result);            //将非空的属性值copy到result对象中去（也就是把你修改了值得那部分替换原来的数据，没有修改的那部分不动他）
        questionDao.saveAndFlush(result);
        log.info("更新问题id为：" + question.getId() + "的问题的内容");
    }
}
