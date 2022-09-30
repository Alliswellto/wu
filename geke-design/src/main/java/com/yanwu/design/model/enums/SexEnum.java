package com.yanwu.design.model.enums;

/**
 * @Description: SexEnum
 * @Author yanwu
 * @Date 2022/8/22 19:11
 * @Version 1.0
 */
public enum SexEnum {

    SEX_0(0, "男"),
    SEX_1(1, "女"),
    SEX_2(2, "未知"),
    ;

    private int type;
    private String name;

    SexEnum(int type, String name) {
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
