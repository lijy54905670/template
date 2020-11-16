package com.xinyuan.ms.service;

import com.xinyuan.ms.common.service.Order;
import com.xinyuan.ms.common.service.PageBean;
import com.xinyuan.ms.common.service.ParamCondition;
import com.xinyuan.ms.common.service.SelectParam;
import com.xinyuan.ms.common.util.EntityUtils;
import com.xinyuan.ms.common.util.ReflectionUtils;
import com.xinyuan.ms.common.util.ResultUtil;
import com.xinyuan.ms.common.web.Conditions;
import com.xinyuan.ms.common.web.Message;
import com.xinyuan.ms.common.web.PageBody;
import com.xinyuan.ms.entity.User;
import com.xinyuan.ms.exception.BaseException;
import com.xinyuan.ms.mapper.BaseJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * 基础Service层
 *
 * @author
 * @since 2018-03-06
 */
@Slf4j  //如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j;
public abstract class BaseService<J extends BaseJpaRepository<T, ID>, T, ID extends Serializable> {

    @Autowired                    //自动导入依赖的bean
    protected J bizRepository;    //注入接口，类型是一个泛型，在使用时具体赋值，提高了代码的可重用性

    @Autowired
    private EntityManager entityManager;

    @Value("${linux.file.upload.path}")
    public String linuxFileUploadPath;

    @Value("${windows.file.upload.path}")
    public String windowsFileUploadPath;

    /**
     * 业务新增方法(初始化和校验)
     *
     * @author 2018-03-06 14:00
     */
    @Transactional(rollbackFor = Exception.class)
    public T save(T entity) throws BaseException {              //传入一个user对象
        String fieldName = "id";

        T jpaResult = bizRepository.saveAndFlush(entity);       //存储传入的user对象、并且立即刷新到数据库中去******************************
        //T jpaResult = bizRepository.save(entity);                  //可以使用save（）方法进行插入，但是使用save（）可能只暂时保留在内存中，直到发出flush或commit命令
        //清空一级缓存
        entityManager.clear();

        T result = null;                                        //创建一个User类型的变量

       if (ReflectionUtils.hasField(jpaResult, fieldName)) {     //当传入的对象中有id这个字段时执行
            /**
             * 用于判断插入的数据是否插入成功
             */
            result = bizRepository.findOne((ID) ReflectionUtils.getFieldValue(jpaResult, fieldName)/*这里会返回一个fieldName属性的属性值*/);
        }
        return result;  //返回一个结果，用于判断是否已经save数据成功
    }

    /**
     * 业务删除方法(初始化和校验)
     *
     * @author 2018-03-06 14:01
     */
    public void remove(ID id) throws BaseException {
        T entity = bizRepository.findOne(id);                   //删除一条记录之前需要判断数据库中是否存在，使用findOne（id）查，如果有的话会返回一个user对象
        if (entity != null) {                                   //如果返回的user对象不是空的，则表示数据库中存在这条记录，可以执行删除方法
            if (ReflectionUtils.hasField(entity, "deleted")) {   //判断查询出来的这个user对象中是否有deleted这个属性，如果有的话则执行下面代码
                ReflectionUtils.invokeSetter(entity, "deleted", 1);  //这里是使用反射，通过setXXX方法将entity中的deleted属性的值设为1
            }
            bizRepository.save(entity);     //将修改好以后的对象再次保存到数据库中（这里调用的save()方法是JpaRepository中的）
        }
    }


    /**
     * 业务更新方法(初始化和校验)
     *
     * @author 2018-03-06 14:59
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(T entity) throws BaseException {
        T result = null;
        if (ReflectionUtils.hasField(entity, "id")) {                       //判断传入的User对象是否存在id属性
            ID id = (ID) ReflectionUtils.getFieldValue(entity, "id");       //获取id的属性值（没有设置的话为null）
            result = bizRepository.findOne(id);                                       //通过得到的值，从数据库中查询对应的记录（如果你save没有给id话，回报错）
        }
        EntityUtils.copyPropertiesIgnoreNull(entity, result);            //将非空的属性值copy到result对象中去（也就是把你修改了值得那部分替换原来的数据，没有修改的那部分不动他）
        //如果你给的要更新的字段在数据库中没有的话，会抛出异常
        bizRepository.saveAndFlush(result);                              //将修改好后的对象保存回数据库
    }

    /**
     * 查询单个方法
     *
     * @author 2018-03-06 14:59
     */
    public T get(ID id) throws BaseException {
        return bizRepository.findOne(id);
    }

    /**
     * 业务条件查询方法(带分页参数)
     *
     * @author 2018-03-08 9:17
     */
    public Page findByCondition(Integer pageNum, Integer pageSize, Sort sort, List<SelectParam> selectParams) {
        /**
         * pageNum:当前页号  pageSize：页面大小    sort：排序方法     selectParams：一个对象，包含查询时使用的（参数键，参数值，查询的类型）
         * */
        int page = 1;
        int limit = 10;
        if (pageNum != null) {
            page = pageNum;
        }
        if (pageSize != null) {
            limit = pageSize;
        }

        Specification querySpecifi = getSpecification(selectParams, false);                              //      没看懂是用来干什么的
        PageBean pageBean = new PageBean(page, limit, sort);
        return bizRepository.findAll(querySpecifi, pageBean);               //执行查询语句
    }

    /**
     * 业务条件查询方法(带分页参数)
     *
     * @author 2018-03-08 9:17
     */
    public Page findByCondition(Integer pageNum, Integer pageSize, List<SelectParam> selectParams) {
        int page = 1;
        int limit = 10;
        if (pageNum != null) {
            page = pageNum;
        }
        if (pageSize != null) {
            limit = pageSize;
        }

        Specification querySpecifi = getSpecification(selectParams, false);
        PageBean pageBean = new PageBean(page, limit, null);
        return bizRepository.findAll(querySpecifi, pageBean);
    }

    /**
     * 不带分页条件查询
     *
     * @author 2018-03-13 16:03
     */
    public List<T> findByCondition(List<SelectParam> selectParams) {
        return bizRepository.findAll(getSpecification(selectParams, false));
    }

    /**
     * 不带分页条件查询
     *
     * @author 2018-03-13 16:03
     */
    public T getByCondition(List<SelectParam> selectParams) {
        List<T> list = bizRepository.findAll(getSpecification(selectParams, false));

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 非业务表的条件查询(不带deleted字段的条件查询)
     *
     * @author 2018-03-21 18:59
     */
    public List<T> findByConditionAndDelete(List<SelectParam> selectParams) {
        return bizRepository.findAll(getSpecification(selectParams, true));
    }

    /**
     * 封装Specification对象
     *
     * @author 2018-03-13 16:04
     */
    private Specification getSpecification(List<SelectParam> selectParams, boolean isDelete) {
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {                               //返回一个匿名的Specification对象
            List<Predicate> predicates = new ArrayList<>();
            if (!isDelete) {
                predicates.add(criteriaBuilder.equal(root.get("deleted"), 0));
            }
            if (selectParams != null) {                                  //查询的条件列表有
                for (SelectParam s : selectParams) {
                    switch (s.getCondition()) {                          //得到查询的类型（=，>,<,>=,<=……）
                        case EQUAL:
                            predicates.add(criteriaBuilder.equal(root.get(s.getParamKey()),
                                    s.getParamValue()));
                            break;
                        case GREATERTHAN:
                            predicates.add(criteriaBuilder.greaterThan(root.get(s.getParamKey()),
                                    (Comparable) s.getParamValue()));
                            break;
                        case LESSTHAN:
                            predicates.add(criteriaBuilder.lessThan(root.get(s.getParamKey()),
                                    (Comparable) s.getParamValue()));
                            break;
                        case LIKE:
                            predicates.add(criteriaBuilder.like(root.get(s.getParamKey()),
                                    "%" + s.getParamValue() + "%"));
                            break;
                        case GREATERTHANEQUAL:
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(s.getParamKey()),
                                    (Comparable) s.getParamValue()));
                            break;
                        case LESSTHANEQUAL:
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(s.getParamKey()),
                                    (Comparable) s.getParamValue()));
                            break;
                        case IN:
                            String key = s.getParamKey();
                            CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get(key));
                            List<Object> list = (List<Object>) s.getParamValue();
                            for (Object id : list) {
                                in.value(id);
                            }
                            predicates.add(in);
                            break;
                        default:
                            break;
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    /**
     * 通用条件查询
     *
     * @param list
     * @return
     */
    public List<SelectParam> getSelectParamList(ArrayList<Conditions> list) {
        List<SelectParam> selectParamList = new ArrayList<>();            //传入的查询条件可有多个（用于存储你设置的参数键（id），参数值（5），查询的类型（EQUAL）  id = 5）
        if (list != null) {                                               //你有Conditions的相关设置（查询类型，字段，值）
            for (Conditions mapBean : list) {
                ParamCondition paramCondition = null;                     //查询类型（枚举类型）
                String condition = mapBean.getCondition();                //从conditions对象中获取得到查询条件（Condition）
                switch (condition) {                                      //匹配你传入的判断条件，匹配成功后给paramCondition对象赋值（如果你没有查询条件，则默认为EQUAL）
                    case "GREATERTHAN":
                        paramCondition = ParamCondition.GREATERTHAN;
                        break;
                    case "LESSTHAN":
                        paramCondition = ParamCondition.LESSTHAN;
                        break;
                    case "LIKE":
                        paramCondition = ParamCondition.LIKE;
                        break;
                    case "GREATERTHANEQUAL":
                        paramCondition = ParamCondition.GREATERTHANEQUAL;
                        break;
                    case "LESSTHANEQUAL":
                        paramCondition = ParamCondition.LESSTHANEQUAL;
                        break;
                    case "IN":
                        paramCondition = ParamCondition.IN;
                        break;
                    default:
                        paramCondition = ParamCondition.EQUAL;
                        break;
                }

                if (!ObjectUtils.isEmpty(mapBean.getValue())) {    //如果你的查询有值得话 （where id = ）等于后面有值
                    /**
                     * 初始化一个有参数键，参数值，以及查询条件的SelectParam对象
                     *
                     * 因为可能where后面的条件有很多，所以需要一个列表进行存储
                     */
                    SelectParam selectParam = new SelectParam(mapBean.getKey(), mapBean.getValue(), paramCondition);
                    selectParamList.add(selectParam);
                }

            }
        }
        return selectParamList;
    }


    public Message upload(MultipartFile file) throws Exception {
        String url;
        String saveDirectoryPath;

        String osName = System.getProperties().getProperty("os.name");
        if (osName.equals("Linux")) {
            saveDirectoryPath = linuxFileUploadPath;
            url = "/upload";
        } else {
            saveDirectoryPath = windowsFileUploadPath;
            url = "d:/images";
        }

        File saveDirectory = new File(saveDirectoryPath);

        if (!saveDirectory.isDirectory() && !saveDirectory.exists()) {
            saveDirectory.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        StringBuilder name;
        String fileName = file.getOriginalFilename();

        if (StringUtils.isEmpty(file)) {
            return ResultUtil.error(2001, "图片格式不对");
        }

        if (file.isEmpty()) {
            return ResultUtil.error(1007, "文件为空");
        }

        String suffix = org.apache.commons.lang.StringUtils.substringAfterLast(fileName, ".");

        FileOutputStream out = null;

        if (suffix.equals("png") || suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("bmp") || suffix.equals("psd")) {
            name = new StringBuilder("/" + uuid + ".");
            name.append(suffix);
            log.info(name + "");
            out = new FileOutputStream(saveDirectoryPath + name.toString());
            url = url + name.toString();
        } else if (suffix.equals("txt")) {
            name = new StringBuilder("/" + fileName);
            out = new FileOutputStream(saveDirectoryPath + name.toString());
            url = saveDirectoryPath + name.toString();
        } else {
            return ResultUtil.error(2001, "图片格式不对");
        }

        out.write(file.getBytes());
        out.flush();
        out.close();

        return ResultUtil.success(url);
    }

    public Page query(PageBody pageBody) {
        Page page;
        Sort sort = sort(pageBody.getOrder());   //new一个Sort对象，并且初始化排序的字段以及类型（desc,asc），如果你没有设置的话默认为通过id进行asc排序

        if (pageBody.getPageBean() != null) {    //判断是否设置了分页属性
            /**
             * findByCondition(当前页号，页面大小，排序方法（sort），查询的参数（conditions）)
             * 如果没有传入分页的相关配置的话：页号（1），页面大小（10）
             */
            page = findByCondition(pageBody.getPageBean().getPageNumber(), pageBody.getPageBean().getPageSize(), sort, getSelectParamList(pageBody.getConditions()));
        } else {
            page = findByCondition(1, Integer.MAX_VALUE, sort, getSelectParamList(pageBody.getConditions()));
        }

        return page;
    }

    public Sort sort(Order order) {
        Sort sort;
        if (order != null) {
            if (("DESC").equals(order.getDirection())) {
                sort = new Sort(Sort.Direction.DESC, order.getProperties());
            } else {
                sort = new Sort(Sort.Direction.ASC, order.getProperties());
            }
        } else {
            sort = new Sort(Sort.Direction.ASC, "id");
        }
        return sort;
    }
}
