package com.atguigu.myzhxy.mapper;

import com.atguigu.myzhxy.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository //方便spring识别扫描到当前接口
public interface AdminMapper extends BaseMapper<Admin> {
}
