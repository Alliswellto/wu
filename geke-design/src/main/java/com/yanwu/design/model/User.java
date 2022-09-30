package com.yanwu.design.model;

import com.yanwu.design.model.enums.RoleEnum;
import com.yanwu.design.model.enums.SexEnum;

import javax.validation.constraints.Email;

/**
 * @Description: User
 * @Author yanwu
 * @Date 2022/8/22 19:10
 * @Version 1.0
 */
public class User {

    private Long id;

    private String qtalk;

    private Long period_id;

    private String name;

    private SexEnum sex;

    private String department;

    @Email
    private String email;

    private String job;

    private RoleEnum roleType;

}
