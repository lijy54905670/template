package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author hwz
 */
@Data     //提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
@Entity   //标明这个类是一个实体类（在数据传输过程中对数据进行封装，相当于一个工具，容器，可以通过其中的set、get、等方法对数据进行存储、传输、管理）
@Table(name = "sys_user")  //定义映射的表，也就是数据库中对应的表（一般如果类名与数据库表名一致的话，可以不写）
public class User implements Serializable {

    @Id    //表示该属性为主键(数据库中表的主键)
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)  //表示主键生成的策略（）
    private Long id;

    @ApiModelProperty(value = "用户名（唯一）", name = "username", example = "username")
    @Column(name = "username", unique = true)  //当实体类中的属性与数据库中的字段不一致的时候，使用@Column标签设置映射的数据库表的列名
                                //unique属性:表示这个字段是否为唯一标识（也就是一个键，但可以不是主键，在数据库中同一个列中只能出现一次）
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "password")
    @Column(name = "password")
    private String password;

    @ApiModelProperty(value = "登录次数", name = "login_count", example = "0")
    @Column(name = "login_count", columnDefinition = "INT DEFAULT 0", insertable = false)
                                    //columnDefinition属性：表示创建表时，该字段创建的SQL语句，一般用于通过Entity生成表定义时使用，如果数据库中表已经建好，该属性没有必要使用
    private Integer loginCount;

    @ApiModelProperty(value = "状态(0,1)，0停用，1启用", name = "status", example = "0")
    @Column(name = "status")
    private Integer status;

    @ApiModelProperty(value = "最后登录时间", name = "loginTime", example = "2018-04-12 10:55:23")
    @Column(name = "last_login_time")
    private Date loginTime;

    @ApiModelProperty(value = "注册时间", name = "createTime", example = "2018-04-12 10:55:23")
    @Column(name = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "会员等级（1.普通会员，2高级会员，3顶级会员）", name = "vip", example = "1")
    @Column(name = "vip", columnDefinition = "INT DEFAULT 1", insertable = false)
    private Integer vip;

    @ApiModelProperty(value = "消费金额", name = "money", example = "0")
    @Column(name = "money", columnDefinition = "Float DEFAULT 0", insertable = false)
    private Float money;

    @ApiModelProperty(value = "头像", name = "icon", example = "/upload/afd5c1ee-409e-43b2-86b4-b239e0e91fd6.jpg")
    @Column(name = "icon")
    private String icon;

    @ApiModelProperty(value = "昵称", name = "nickname", example = "龙傲天")
    @Column(name = "nickname")
    private String nickname;

    @ApiModelProperty(value = "简介", name = "introduction", example = "简介")
    @Column(name = "introduction")
    private String introduction;

    @ApiModelProperty(value = "电话号码", name = "phone", example = "15666666666")
    @Column(name = "phone")
    private String phone;

    @ApiModelProperty(value = "微信openId", name = "openId", example = "wxAAAAAA123456789")
    @Column(name = "open_id")
    private String openId;

    @ApiModelProperty(value = "性别，1男0女", name = "sex", example = "1")
    @Column(name = "sex")
    private Integer sex;

    @ApiModelProperty(value = "年龄", name = "age", example = "18")
    @Column(name = "age")
    private Integer age;

    @ApiModelProperty(value = "收藏话题数量", name = "collectTopicNum", example = "0")
    @Column(name = "collect_topic_num", columnDefinition = "INT DEFAULT 0", insertable = false)
                                                                           //insertable:表示在使用”INSERT”语句插入数据时，是否需要插入该字段的值
                                                                           //insertable和updateable一般多用于只读属性，例如主键，外键，这些属性一般都是插入数据时自动生成
    private Integer collectTopicNum;

    @ApiModelProperty(value = "我关注的人数量", name = "concertUserNum", example = "0")
    @Column(name = "concert_user_num", columnDefinition = "INT DEFAULT 0", insertable = false)
    private Integer concertUserNum;

    @ApiModelProperty(value = "关注我的人数量", name = "concertedUserNum", example = "0")
    @Column(name = "concerted_user_num", columnDefinition = "INT DEFAULT 0", insertable = false)
    private Integer concertedUserNum;

    @ApiModelProperty(value = "我收到的赞数量", name = "likedNum", example = "0")
    @Column(name = "liked_num", columnDefinition = "INT DEFAULT 0", insertable = false)
    private Integer likedNum;

    @ApiModelProperty(value = "我被收藏的数量", name = "collectedNum", example = "0")
    @Column(name = "collected_num", columnDefinition = "INT DEFAULT 0", insertable = false)
    private Integer collectedNum;

    @ApiModelProperty(value = "删除标识 0未删除 1已删除", name = "deleted", example = "0")
    @Column(name = "deleted")
    private Integer deleted = 0;


}
