package com.xinyuan.ms.service;

import com.xinyuan.ms.common.service.ParamCondition;
import com.xinyuan.ms.common.service.SelectParam;
import com.xinyuan.ms.common.web.Conditions;
import com.xinyuan.ms.entity.Option;
import com.xinyuan.ms.mapper.OptionDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OptionService extends BaseService<OptionDao,Option,Long>{

    public void deleteByQId(Long qId){
        List<Option> optionList =findOptionByQId(qId);     //得到问题的全部选项
        if (!optionList.isEmpty()){
            for (Option option: optionList) {
                remove(option.getId());                                 //调用删除方法
            }
        }
    }

    public void save1(Option option){//题目是新建的
                save(option);
    }

    public void update1(List<Option> options,Long qId) {
        for (Option option : options) {
            int deleted = option.getDeleted();
            if (deleted == 1) {
                remove(option.getId());
            } else if (option.getId() == null) {
                option.setQId(qId);
                save1(option);
            } else {
                update(option);
            }
        }
    }


        /**
         * 通过问题id查找对应选项
         * @param qId
         * @return
         */
        public List<Option> findOptionByQId(Long qId) {

           /* ArrayList<Conditions> conditions = new ArrayList<>();       //新建一个参数集合
            Conditions condition = new Conditions();                   //新增一个参数，用于通过问题id查询选项
            condition.setKey("qId");
            condition.setValue(qId);
            condition.setCondition("EQUAL");
            conditions.add(condition);

            List<Option> byCondition = findByCondition(getSelectParamList(conditions)); //调用baseService中的不带分页的查询方法
*/
           List<SelectParam> selectParamList = new ArrayList<>();
           SelectParam selectParam = new SelectParam("qId",qId, ParamCondition.EQUAL);     //新建一个查询参数对象
           selectParamList.add(selectParam);
            List<Option> byCondition = findByCondition(selectParamList);
            return byCondition;                                                                       //返回查询到的选项列表
        }
    }

