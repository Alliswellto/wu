package com.yanwu.design;

import com.yanwu.design.model.enums.SexEnum;

/**
 * @Description: Main
 * @Author yanwu
 * @Date 2022/8/16 15:25
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
//        System.out.println(SexEnum.valueOf("男"));
        for (SexEnum value : SexEnum.values()) {
            if (value.getName().equals("男")) {
                System.out.println(value.getType());
            }

        }
    }
}