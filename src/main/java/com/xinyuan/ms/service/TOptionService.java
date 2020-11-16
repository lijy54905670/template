package com.xinyuan.ms.service;

import com.xinyuan.ms.entity.TOption;
import com.xinyuan.ms.mapper.TOptionDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TOptionService extends BaseService<TOptionDao,TOption,Long>{

    /**
     * 保存选项
     * @param tOptions
     * @param topicId
     */
    public void saveOptions(List<TOption> tOptions,Long topicId){
        for (TOption tOption : tOptions){
            tOption.setTId(topicId);             //给选项设置题目id
            save(tOption);
        }
    }

    /**
     * 更新选项
     * @param tOptions
     */
    public void updateOptions(List<TOption> tOptions,Long topicId){
        for (TOption tOption: tOptions){
            if (tOption.getId() == null ||tOption.getId() ==0){

                save(tOption);
            }
            update(tOption);
        }
    }
}
