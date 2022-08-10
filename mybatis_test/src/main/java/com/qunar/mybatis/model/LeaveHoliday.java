package com.qunar.mybatis.model;

import lombok.*;

import java.util.Date;

/**
 * @ClassName LeaveHoliday * @Description TODO
 * @Author tako
 * @Date 10:47 2022/8/9
 * @Version 1.0
 **/
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LeaveHoliday {

    private Long id;

    private Long staffId;

    private Date startDate;
    
    private Date endDate;

    private Integer dayNum;

    private Integer type;

    private String area;

    public LeaveHoliday(Long staffId, Date startDate, Date endDate, Integer dayNum, Integer type, String area) {
        this.staffId = staffId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayNum = dayNum;
        this.type = type;
        this.area = area;
    }
}
