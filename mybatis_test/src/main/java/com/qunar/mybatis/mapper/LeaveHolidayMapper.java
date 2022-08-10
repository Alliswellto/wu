package com.qunar.mybatis.mapper;

import com.qunar.mybatis.model.Employee;
import com.qunar.mybatis.model.LeaveHoliday;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LeaveHolidayMapper * @Description TODO
 * @Author tako
 * @Date 14:47 2022/8/9
 * @Version 1.0
 **/
@Mapper
public interface LeaveHolidayMapper {

    /**
     * 3、查询2号员工的请假详单
     * @param staffId
     * @return
     */
//    @Select(value = "select id, staff_id, start_date, end_date, day_num, type, area from leave_holiday " +
//            "where staff_id = #{staffId} order by start_date;")
    List<LeaveHoliday> queryEmployeeHolidayDetailById(@Param("staffId") Integer staffId);

    /**
     * 7、查询请年假天数小于3天的员工id号
     * @param count
     * @return
     */
//    @Select(value = "select a.staff_id from (select staff_id, count(day_num) cnt, type from leave_holiday where " +
//            "type = 1 group by staff_id, type) a where a.cnt < #{count}")
    List<Long> queryEmployeeAnnualNumLessCount(@Param("count") Integer count);

    /**
     * 10、统计查询工号2，4的已请年假天数和剩余年假天数
     * @param staffId
     * @return
     */
//    @Select(value = "select a.staff_id, ifnull(a.annual_num, 0) annual_num, ifnull(b.day_num, 0) day_num " +
//            "from holiday a  join (select staff_id, sum(day_num) day_num, type from leave_holiday " +
//            "where staff_id = #{staffId} and type = #{type}) b on a.staff_id = b.staff_id where annual_num != 0 " +
//            "or day_num != 0 group by a.staff_id, a.annual_num, b.day_num")
    Map<String, Integer> queryEmployeeHolidayNumById(@Param("staffId") Integer staffId, @Param("type") Integer type);

    /**
     * 更新请假详情
     * @param leaveHoliday
     * @return
     */
//    @Insert(value = "insert into leave_holiday(staff_id, start_date, end_date, day_num, type, area) " +
//            "values(#{staffId}, #{startDate}, #{endDate}, #{dayNum}, #{type}, #{area})")
    Integer insertLeaveHolidayDetail(LeaveHoliday leaveHoliday);
}
