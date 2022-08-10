package com.qunar.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName HolidayMapper * @Description TODO
 * @Author tako
 * @Date 14:55 2022/8/9
 * @Version 1.0
 **/
@Mapper
public interface HolidayMapper {

    /**
     * 4、更新3号员工的年假剩余数为3
     *
     * @param staffId
     */
//    @Update(value = "update holiday set annual_num = #{annualNum} where staff_id = #{staffId}")
    Integer updateEmployeeHolidayById(@Param("staffId") Integer staffId, @Param("annualNum") Integer annualNum);

    /**
     * 查询剩余病假是否充足
     * @param staffId
     * @return
     */
//    @Select(value = "select sick_num from holiday where staff_id = #{staffId}")
    Integer queryRemainSickNumById(@Param("staffId") Integer staffId);

    /**
     * 更新剩余病假数
     * @param staffId
     * @param cnt
     * @return
     */
//    @Update(value = "update holiday set sick_num = sick_num - #{cnt} where staff_id = #{staffId}")
    Integer updateEmployeeSickNumById(@Param("staffId") Integer staffId, @Param("cnt") Integer cnt);
}
