package com.qunar.mybatis.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName HolidayService * @Description TODO
 * @Author tako
 * @Date 14:55 2022/8/9
 * @Version 1.0
 **/
public interface HolidayService {

    /**
     * 4、更新3号员工的年假剩余数为3
     *
     * @param staffId
     */
    Integer updateEmployeeHolidayById(Integer staffId, Integer annualNum);

    /**
     * 11、查询剩余年假数
     * @param staffId
     * @return
     */
    Integer queryRemainSickNumById(Integer staffId);

    /**
     * 更新剩余病假数
     * @param staffId
     * @param cnt
     * @return
     */
    Integer updateEmployeeSickNumById(@Param("staffId") Integer staffId, @Param("cnt") Integer cnt);
}
