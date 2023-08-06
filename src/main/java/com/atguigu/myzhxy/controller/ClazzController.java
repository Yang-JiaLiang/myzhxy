package com.atguigu.myzhxy.controller;

import com.atguigu.myzhxy.pojo.Clazz;
import com.atguigu.myzhxy.service.ClazzService;
import com.atguigu.myzhxy.util.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "班级控制器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @ApiOperation("回显学生管理中搜索条件的班级选项")
    @GetMapping("/getClazzs")
    private Result getClazzs() {
        List<Clazz> clazzs = clazzService.list();
        return Result.ok(clazzs);
    }

    @ApiOperation("删除或批量删除班级信息")
    @DeleteMapping("/deleteClazz")
    private Result deleteClazz(
            @ApiParam("要删除的班级") @RequestBody List<Integer> ids
    ) {
        clazzService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("添加或修改班级信息，带id是修改，不带id是添加")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(
            @ApiParam("Json的clazz对象") @RequestBody Clazz clazz
    ) {
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }


    @ApiOperation("查询班级信息,分页带条件")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件") Clazz clazz
    ) {
        //设置分页信息
        Page<Clazz> page = new Page<>(pageNo, pageSize);
        IPage<Clazz> iPage = clazzService.getClazz(page, clazz);
        return Result.ok(iPage);
    }

}
