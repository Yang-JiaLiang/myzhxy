package com.atguigu.myzhxy.controller;


import com.atguigu.myzhxy.pojo.Student;
import com.atguigu.myzhxy.service.StudentService;
import com.atguigu.myzhxy.util.MD5;
import com.atguigu.myzhxy.util.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @ApiOperation("删除或批量学生信息")
    @DeleteMapping("/delStudentById")
    private Result delStudentById(
            @ApiParam("要删除的学生的id集合") @RequestBody List<Integer> ids
    ) {
        studentService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("添加或修改学生，带id是修改，不带id是添加")
    @PostMapping("/addOrUpdateStudent")
    private Result addOrUpdateStudent(
            @ApiParam("Json的student对象") @RequestBody Student student
    ) {
        String password = student.getPassword();
        if (!StringUtils.isEmpty(password)) {
            student.setPassword(MD5.encrypt(password));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @ApiOperation("查询学生信息,分页带条件")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    private Result getStudentByOpr(
            @ApiParam("页码") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件") Student student
    ) {
        Page<Student> page = new Page<>(pageNo, pageSize);
        IPage<Student> iPage = studentService.getStudents(page, student);
        return Result.ok(iPage);
    }

}
