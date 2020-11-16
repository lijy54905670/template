package com.xinyuan.ms.service;

import com.xinyuan.ms.common.service.SelectParam;
import com.xinyuan.ms.common.web.Conditions;
import com.xinyuan.ms.entity.Classify;
import com.xinyuan.ms.mapper.ClassifyDao;
import com.xinyuan.ms.web.vo.Menu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassifyService extends BaseService<ClassifyDao, Classify,Long> {

    /**
     * 查询分类
     * @param conditions
     * @return
     */
    public List<Classify> query(List<Conditions> conditions){
        //通过传入的deleted来判断是查询全部还是查询未删除的

        boolean flag = false;                                      //用于判断是否是通过搜索查找分类
        for (Conditions condition: conditions){
            if (condition.getCondition().equals("LIKE")) {         //如果是搜索，则需要把他的父节点都找出来
                flag = true;
                break;
            }
        }


        //根据查询条件查询
        List<SelectParam> selectParamList = getSelectParamList((ArrayList<Conditions>) conditions);
        List<Classify> classify = findByConditionAndDelete(selectParamList);


        if (flag == true){        //通过模糊查询查找分类
            List<Classify> classifies = new ArrayList<>();  //存放查询得到的结果
            for (Classify classify1 : classify){
                classifies.add(classify1);
                Long sId = classify1.getSId();
                while(sId != 0){                  //找到父节点，直到到达根节点
                    Classify classify2 = get(sId);
                    classifies.add(classify2);
                    sId = classify2.getSId();
                }
            }
            return classifies;                   //返回根据搜索的到的结果
        }else
            return classify;                     //返回正常的所有的分类
    }

    /**
     * 添加分类，同时修改父类
     * @param classify
     */
    public void addClassify(Classify classify){

      /*  List<SelectParam> selectParams = new ArrayList<>();
        SelectParam selectParam = new SelectParam("sId",classify.getSId(), ParamCondition.EQUAL);
        selectParams.add(selectParam);

        List<Classify> all = findByConditionAndDelete(selectParams); //查询父节点所有的子节点(包括已经删除了的节点)

        Optional<Integer> collect = all.stream()
                .map(Classify::getSort).max(Integer::compare);    //得到父节点下所有排序的最大值

        Integer count = collect.orElse(0);                //如果父节点下没有子节点，则设为0*/

        Integer max = bizRepository.maxSort(classify.getSId());  //获取父节点下的最大排序
        int count = 0;
        if (max != null){
            count = max;
        }
        count++;                                                //当前节点排序为父节点下子节点数目+1

        classify.setSort(count);                          //设置当前分类所在级别的排序
        save(classify);     //保存新建分类

        Long sId = classify.getSId();                //得到父节点
        Classify parent = get(sId);                  //如果父节点之前是末尾节点，则修改
        if(parent.getLast() == 1){
            parent.setLast(0);                       //如果父节点之前是尾结点，则修改为不是
            update(parent);                          //更新父节点
        }
    }

    /**
     * 根据分类id得到分类名
     * @param id
     * @return
     */
    public String findClassifyNameById(Long id){
        Classify classify = get(id);
        String name = null;
        if (classify!=null)
           name =  classify.getName();
        return name;
    }

    /**
     * 返回递归树
     */
    public List<Menu> recursionTree(List<Conditions> conditions){

        List<Classify> classifies = query(conditions);   //得到所以的分类
        List<Menu> menuList = new ArrayList<>();        //将得到的分类装入树对象中
        for (Classify classify : classifies){
            Menu menu = new Menu();
            menu.setClassify(classify);
            menuList.add(menu);
        }


        //获取其中的根节点,并放入Menu对象中
        List<Menu> rootMenuLists = new ArrayList<>();  //用于保存根节点
        for (Menu menuNode : menuList){
            if (menuNode.getClassify().getSId() == 0){         //找到所有的根节点
               rootMenuLists.add(menuNode);
            }
        }

        //根据父节点找到对应的子节点
        List<Menu> treeMenus = new ArrayList<>();       //用于存储全部的树形结构
        for (Menu menuNode : rootMenuLists){               //遍历根节点，建立子树形结构
            menuNode = buildChildTree(menuNode,menuList);  //调用建立子树方法，返回一个以menuNode为根节点的树形结构
            treeMenus.add(menuNode);
        }

        return treeMenus;             //返回得到的树形结构
    }

    /**
     * 递归，建立子树形结构
     * @param pNode 父节点
     * @return  pNode 设置好子节点的父节点
     */
    public Menu buildChildTree(Menu pNode,List<Menu> menuList){
        List<Menu> childMenus = new ArrayList<>();           //用于保存父节点pNode的子节点信息

        for (Menu menuNode : menuList){                      //遍历所有分类
            if (menuNode.getClassify().getSId() == pNode.getClassify().getId()){  //找到pNode节点的所有子节点
                childMenus.add(buildChildTree(menuNode,menuList));             //将当前节点加入到父节点的子节点集合中，并且以当前节点为父节点继续找子节点
            }
        }
        pNode.setChildren(childMenus);                                        //找到pNode节点的子节点后，使用setter方法
        return pNode;
    }
}
