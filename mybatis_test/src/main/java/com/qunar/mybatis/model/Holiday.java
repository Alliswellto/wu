package com.qunar.mybatis.model;

import lombok.*;

/**
 * @ClassName holiday * @Description TODO
 * @Author tako
 * @Date 10:45 2022/8/9
 * @Version 1.0
 **/
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {

    private Integer id;

    private Integer staffId;

    private Integer sickNum;

    private Integer annualNum;
}
