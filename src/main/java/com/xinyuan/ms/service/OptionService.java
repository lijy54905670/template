package com.xinyuan.ms.service;

import com.xinyuan.ms.common.util.EntityUtils;
import com.xinyuan.ms.common.util.ReflectionUtils;
import com.xinyuan.ms.entity.Option;
import com.xinyuan.ms.entity.Question;
import com.xinyuan.ms.mapper.OptionDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class OptionService {

    @Autowired
    OptionDao optionDao;

    public void save(Question question, List<Option> options, Long qId) {
        if(!options.isEmpty()) {
            if (question.getDeleted() == 1){  //如果问题删除的话，删除问题的全部选项
                List<Option> optionList = optionDao.findOptionByQIdEquals(question.getId()); //得到问题的全部选项
                if (!optionList.isEmpty()){
                    for (Option option: optionList) {
                        delete(option.getId());
                    }
                }
            } else if (qId != null) {     //题目是新建的
                for (Option option: options) {
                    add(option,qId);   //将问题选项添加到选项表
                }
            } else {
                Long gId = question.getId();              //获取当前修改的题目id
                for (Option o : options) {
                    Long oId = o.getId();      //获取需要修改的选项id
                    if (oId != null) {        //修改原有的选项,oId有的时候
                        if (o.getDeleted() != 1) {    //如果当前选项不是是要删除的
                            update(o,gId);            //更新选项
                        }else {
                            delete(oId);
                        }
                    }else{
                       add(o,gId);
                    }
                }
            }
        }
    }

    /**
     * 添加选项
     * @param option 选项
     * @param qId 问题id
     */
    public void add(Option option,Long qId){
            option.setQId(qId);
            optionDao.saveAndFlush(option);
    }

    /**
     * 更新选项
     * @param option  新的选项对象
     * @param qId  选项所属的问题id
     */
    public void update(Option option,Long qId){
        option.setQId(qId);                                     //获取当前选项id
        Option result = optionDao.findOne(option.getId());      //得到原来的选项
        if (result != null) {
            System.out.println(result.getContent() + "---------");
            System.out.println(option.getQId() + "修改选项的问题id");

            EntityUtils.copyPropertiesIgnoreNull(option, result);         //将传入的选项复制到原来的选项中
            optionDao.saveAndFlush(result);
        }
    }

    /**
     * 删除选项
     * @param oId  需要删除的选项id
     */
    public void delete(Long oId){
        Option result = optionDao.getOne(oId);
        if (result != null){
            if (ReflectionUtils.hasField(result, "deleted")) {   //判断查询出来的这个user对象中是否有deleted这个属性，如果有的话则执行下面代码
                ReflectionUtils.invokeSetter(result, "deleted", 1);  //这里是使用反射，通过setXXX方法将entity中的deleted属性的值设为1
            }
            optionDao.save(result);
        }
    }
}
