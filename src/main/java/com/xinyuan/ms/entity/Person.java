package com.xinyuan.ms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_person")
public class Person {

    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "0")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ApiModelProperty(value = "用户名",name = "userName",example = "userName")
    @Column(name = "user_name",unique = true)
    private String userName;

    @ApiModelProperty(value = "密码",name = "password",example = "password")
    @Column(name = "password")
    private String password;
}
