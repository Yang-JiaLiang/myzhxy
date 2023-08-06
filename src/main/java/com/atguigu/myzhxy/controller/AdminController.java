package com.atguigu.myzhxy.controller;

import com.atguigu.myzhxy.pojo.Admin;
import com.atguigu.myzhxy.service.AdminService;
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

@Api(tags = "管理员控制器")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @ApiOperation("删除或批量删除管理员信息")
    @DeleteMapping("/deleteAdmin")
    private Result deleteAdmin(
            @ApiParam("要删除的管理员的id集合") @RequestBody List<Integer> ids
    ) {
        adminService.removeByIds(ids);
        return Result.ok();
    }


    @ApiOperation(("添加和修改管理员信息，含id是修改，不含是添加"))
    @PostMapping("/saveOrUpdateAdmin")
    private Result saveOrUpdateAdmin(
            @ApiParam("Json的Admin对象") @RequestBody Admin admin
    ) {
        if (admin != null) {
            String password = admin.getPassword();
            String encrypt = MD5.encrypt(password);
            admin.setPassword(encrypt);
            adminService.saveOrUpdate(admin);
        }

        return Result.ok();
    }

    @ApiOperation("管理员信息查询，分页带条件")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    private Result getAllAdmin(
            @ApiParam("页码") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("分页条件") String adminName
    ) {
        Page<Admin> page = new Page<>(pageNo, pageSize);
        IPage<Admin> iPage = adminService.getAdmins(page, adminName);

        return Result.ok(iPage);
    }
}
