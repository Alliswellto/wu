package com.yanwu.design.model.enums;

/**
 * @Description: RoleEnum
 * @Author yanwu
 * @Date 2022/8/22 19:21
 * @Version 1.0
 */
public enum RoleEnum {

    ROLE_0(0, "管理员"),
    ROLE_1(1, "学员"),
    ROLE_2(2, "讲师"),
    ROLE_3(3, "导师"),
    ROLE_4(4, "其他"),
    ;

    private int type;
    private String name;

    RoleEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
