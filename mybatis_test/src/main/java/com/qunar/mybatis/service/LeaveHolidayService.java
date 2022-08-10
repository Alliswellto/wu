package com.qunar.mybatis.service;

import com.qunar.mybatis.model.Employee;
import com.qunar.mybatis.model.LeaveHoliday;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LeaveHolidayService * @Description TODO
 * @Author tako
 * @Date 14:47 2022/8/9
 * @Version 1.0
 **/
public interface LeaveHolidayService {

    /**
     * 3、查询2号员工的请假详单
     * @param staffId
     * @return
     */
    List<LeaveHoliday> queryEmployeeHolidayDetailById(Integer staffId);

    /**
     * 7、查询请年假天数小于3天的员工id号
     * @param count
     * @return
     */
    List<Integer> queryEmployeeAnnualNumLessCount(Integer count);

    /**
     * 10、统计查询工号2，4的已请年假天数和剩余年假天数
     * @param staffId
     * @return
     */
    Map<String, Integer> queryEmployeeHolidayNumById(Integer staffId, Integer type);

    /**
     * 11、更新请假详情
     * @param leaveHoliday
     * @return
     */
    Integer insertLeaveHolidayDetail(LeaveHoliday leaveHoliday);
}
