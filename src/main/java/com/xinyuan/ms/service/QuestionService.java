package com.xinyuan.ms.service;

import com.xinyuan.ms.common.service.SelectParam;
import com.xinyuan.ms.common.web.PageBody;
import com.xinyuan.ms.entity.Answer;
import com.xinyuan.ms.entity.Option;
import com.xinyuan.ms.entity.Question;
import com.xinyuan.ms.mapper.QuestionDao;
import com.xinyuan.ms.web.request.SaveConditionsAndUId;
import com.xinyuan.ms.web.request.SaveQuestionOption;
import com.xinyuan.ms.web.vo.NextQuestion;
import com.xinyuan.ms.web.vo.QuestionOptionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionService extends BaseService<QuestionDao, Question, Long> {

    @Autowired
    OptionService optionService;

    @Autowired
    AnswerService answerService;

    /**
     * 保存问题和选项
     * @param saveQuestionOptionList
     */
    public void saveQuestionOption(List<SaveQuestionOption> saveQuestionOptionList) {
        if (!saveQuestionOptionList.isEmpty()) {                            //传入的参数不为空则继续
            for (SaveQuestionOption saveQuestionAnswer : saveQuestionOptionList) {
                Question question = saveQuestionAnswer.getQuestion();       //获取Question对象
                List<Option> list = saveQuestionAnswer.getOptionList();     //获取Option对象列表

                save(question, list);                                      //如果是新增问题则返回问题id
            }
        }
    }

    /**
     * 保存问题（新增，删除，修改）
     *
     * @param question
     */
    public void save(Question question, List<Option> options) {
        Long id = question.getId();             //获取题目id,如果为空则表示新增
        int deleted = question.getDeleted();    //获取删除标志
        Long resultId = null;                   //用于返回，如果不为空，则表示是新增的问题
        if (id == null) {
            Question result = save(question);   //得到插入的记录的id，用于选项插入
            resultId = result.getId();
            if (options != null && !options.isEmpty()) { //将新添加的题目的选项保存
                for (Option option : options) {
                    option.setQId(resultId);
                    optionService.save1(option);
                }
            }
        } else if (deleted == 1) {
            remove(id);                                  //删除问题
            optionService.deleteByQId(id);               //这里调用选项删除方法，通过问题id
        } else {
            update(question);                      //更新问题
            if (options != null && !options.isEmpty()) {
                optionService.update1(options, question.getId());   //如果选项是新增的，则需要
                //如果选项列表需要更新，则更新（增删改查）
            }
        }
    }

    /**
     * 下一题
     *
     * @param saveConditionsAndUId
     */
    public NextQuestion next(SaveConditionsAndUId saveConditionsAndUId) {
        Long uId = saveConditionsAndUId.getUId();    //得到当前用户id

        PageBody pageBody = new PageBody();             //新建一个不给conditions赋值的pageBody
        pageBody.setOrder(saveConditionsAndUId.getOrder());
        pageBody.setPageBean(saveConditionsAndUId.getPageBean());

        Page<Question> page = query(pageBody);                          //分页查询问题表
        Long qId = page.getContent().get(0).getId();                    //获得查询到的id（问题每次只会查到一个）
        Answer answer = null;
        List<Option> options = null;
        if (qId != null) {
            options = optionService.findOptionByQId(qId);                //通过问题id查询对应的选项列表
           answer = answerService.findAnswerByQIdAndUid(qId, uId);  //通过用户id和问题id，查找用户是否答过这题

        }


        Long oId = null;                                                //用于保存用户这题是否答过
        if (answer != null)
            oId = answer.getOId();                                      //如果答过这题，则把原来选择的选项保存到oId

        //同注入一个nextQuestion实例，将问题，选项列表，答题情况一起保存并返回
        NextQuestion nextQuestion = new NextQuestion();
        nextQuestion.setAnswer(oId);
        nextQuestion.setOptions(options);
        nextQuestion.setQuestion(page);

        return nextQuestion;                                            //返回下一题、选项，答题记录

    }

    /**
     * 查询全部题目（不包含已删除的）
     *
     * @return  所有题目及其选项
     */
    public List<QuestionOptionVo> queryAll() {

        List<SelectParam> selectParams = new ArrayList<>();  //定义一个空的查询参数对象，用于查询没有被删除的选项

          List<Question> questions = bizRepository.findQuestionsByDeletedOrderById(0);   //获取所有未删除的问题，按id排序（0表示为被删除）
          List<Option> optionList = optionService.findByCondition(selectParams);          //获取所有未删除的选项

        List<QuestionOptionVo> questionOptionVos = new ArrayList<>();   //创建一个集合，用户保存问题和选项
          for (int i = 0; i < questions.size(); i++){

              QuestionOptionVo questionOptionVo= new QuestionOptionVo();     //用于保存问题和选项
              List<Option> options = new ArrayList<>();                      //用于保存本题的选项

              for (int j = 0; j < optionList.size(); j++){                   //通过循环，得到每个问题的选项
                  Long qId = questions.get(i).getId();                       //得到问题id
                  Long oQId = optionList.get(j).getQId();                    //得到选项中的问题id
                  if (qId == oQId){
                      options.add(optionList.get(j));                        //将本问题的选项保存到一个列表中
                  }
              }

              questionOptionVo.setQuestion(questions.get(i));                //保存问题
              questionOptionVo.setOptions(options);                          //保存问题选项
              questionOptionVos.add(questionOptionVo);                       //将这个问题选项对象放入列表中
          }
        return questionOptionVos;                                    //返回所有问题及其选项
    }
}
