package com.atguigu.myzhxy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName tb_admin
 */
@TableName(value = "tb_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Admin implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private char gender;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String telephone;

    /**
     *
     */
    private String address;

    /**
     *
     */
    private String portraitPath;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}