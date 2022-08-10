package com.qunar.mybatis.model;

import lombok.*;

/**
 * @ClassName Employee * @Description TODO
 * @Author tako
 * @Date 10:43 2022/8/9
 * @Version 1.0
 **/
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Long id;

    private Long staffId;

    private String name;

    private String mobile;

    private String area;

    private Integer gender;

    private Integer isValid;

    public Employee(Long staffId, String name, String mobile, String area, Integer gender, Integer isValid) {
        this.staffId = staffId;
        this.name = name;
        this.mobile = mobile;
        this.area = area;
        this.gender = gender;
        this.isValid = isValid;
    }
}
