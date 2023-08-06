package com.atguigu.myzhxy.controller;

import com.atguigu.myzhxy.pojo.Grade;
import com.atguigu.myzhxy.service.GradeService;
import com.atguigu.myzhxy.util.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @ApiOperation("回显搜索条件中的年级选项")
    @GetMapping("/getGrades")
    private Result getGrades() {
        List<Grade> grades = gradeService.list();
        return Result.ok(grades);
    }

    @ApiOperation("删除Grade信息")
    @DeleteMapping("/deleteGrade")
    private Result deleteGrade(
            @ApiParam("要删除的所有Grade的id的Json集合") @RequestBody List<Integer> ids
    ) {
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("新增或修改grade，有id属性是修改，无id属性是新增")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(
            @ApiParam("Json的grade对象") @RequestBody Grade grade
    ) {
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    @ApiOperation("根据年级名称模糊查询，带分页")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(
            @ApiParam("分页查询的页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("分页查询的页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("分页模糊匹配的名称") String gradeName
    ) {

        //分页
        Page<Grade> page = new Page<>(pageNo, pageSize);
        IPage<Grade> pageRs = gradeService.getGradeByOpr(page, gradeName);

        return Result.ok(pageRs);
    }
}
