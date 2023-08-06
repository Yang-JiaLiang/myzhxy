package com.atguigu.myzhxy.controller;


import com.atguigu.myzhxy.pojo.Teacher;
import com.atguigu.myzhxy.service.TeacherService;
import com.atguigu.myzhxy.util.MD5;
import com.atguigu.myzhxy.util.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("删除或批量删除教师信息")
    @DeleteMapping("/deleteTeacher")
    private Result deleteTeacher(
            @ApiParam("要删除的教师的id集合") @RequestBody List<Integer> ids
    ) {
        teacherService.removeByIds(ids);
        return Result.ok();
    }


    @ApiOperation("新增或修改教师信息，含id是修改，不含是新增")
    @PostMapping("/saveOrUpdateTeacher")
    private Result saveOrUpdateTeacher(
            @ApiParam(value = "Json的Teacher对象") @RequestBody Teacher teacher
    ) {

        if (teacher != null) {
            String password = teacher.getPassword();
            String encrypt = MD5.encrypt(password);
            teacher.setPassword(encrypt);
        }
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }


    @ApiOperation("教师信息查询，分页带条件")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    private Result getTeachers(
            @ApiParam("页码") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件") Teacher teacher
    ) {

        Page<Teacher> page = new Page<>(pageNo, pageSize);
        IPage<Teacher> iPage = teacherService.getTeachers(page, teacher);
        return Result.ok(iPage);
    }

}
