package com.xinyuan.ms.web.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.xinyuan.ms.common.util.ResultUtil;
import com.xinyuan.ms.common.web.Message;
import com.xinyuan.ms.common.web.PageBody;
import com.xinyuan.ms.entity.User;
import com.xinyuan.ms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: hzx
 */
@Api(description = "UserController")
@RestController        //@Controller注解（定义了这个类是一个控制器类） + @ResponseMapping注解（加上@esponsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中）
                        //负责将用户发来的URL请求转发到对应的服务接口（service层）
@RequestMapping("/user")  //提供路由信息，负责URL到Controller中的具体函数的映射。
public class UserController {

    @Autowired    //自动导入依赖的bean,将spring容器中的bean注入到定义的变量中，一般通过类型，和名字来注入
    private UserService userService;

    @ApiOperation(value = "保存", notes = "保存") //给这个方法添加说明
    @RequestMapping(value = "save", method = RequestMethod.POST)   //使用@RequestMapping注解标识这个方法是处理请求的处理器
                                                                   //value定义这个方法处理的路径是什么
                                                                   //method定义你接受浏览器传来的什么请求
    public ResponseEntity<Message> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseEntity<Message> delete(@RequestBody List<Long> ids) {
        userService.removeList(ids);
        return ResponseEntity.ok(ResultUtil.success());
    }


    @ApiOperation(value = "删除1", notes = "删除1")
    @RequestMapping(value = "delete1", method = RequestMethod.POST)
    public ResponseEntity<Message> delete1(@RequestBody Long ids) {
        userService.remove(ids);
        return ResponseEntity.ok(ResultUtil.success());
    }

    @ApiOperation(value = "更新", notes = "更新")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<Message> update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok(ResultUtil.success());
    }


    @ApiOperation(value = "条件查询", notes = "条件查询")  //给方法添加说明
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody PageBody pageBody) {       //PageBody:查询条件、排序条件、分页的配置
        Page<User> page = null;
        try {
            page = userService.query(pageBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(page);
    }

    @ApiOperation(value = "条件查询", notes = "条件查询")  //给方法添加说明
    @RequestMapping(value = "query2", method = RequestMethod.POST)
    public ResponseEntity query2(HttpServletResponse response, @RequestBody PageBody pageBody) {       //PageBody:查询条件、排序条件、分页的配置

        Page<User> page = null;
        try {
            page = userService.query(pageBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(page);
    }
}
