package com.qunar.mybatis.controller;

import com.qunar.mybatis.model.Employee;
import com.qunar.mybatis.model.LeaveHoliday;
import com.qunar.mybatis.service.LeaveHolidayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LeaveHolidayController * @Description TODO
 * @Author tako
 * @Date 16:08 2022/8/9
 * @Version 1.0
 **/
@RestController
@RequestMapping("/leaveHoliday")
public class LeaveHolidayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveHolidayController.class);

    @Autowired
    private LeaveHolidayService leaveHolidayService;

    @RequestMapping("/queryDetail")
    public void queryEmployeeHolidayDetailById() {
        List<LeaveHoliday> leaveHolidays = leaveHolidayService.queryEmployeeHolidayDetailById(2);
        if (leaveHolidays != null && leaveHolidays.size() > 0) {
            LOGGER.info("查询成功！");
            leaveHolidays.forEach(System.out::println);
        } else {
            LOGGER.error("查询失败！");
        }
    }

    @RequestMapping("/queryByAnnualNum")
    public void queryEmployeeAnnualNumLessCount() {
        List<Long> ids = leaveHolidayService.queryEmployeeAnnualNumLessCount(3);
        if (ids != null && ids.size() > 0) {
            LOGGER.info("查询成功！");
            ids.forEach(System.out::println);
        } else {
            LOGGER.error("查询失败！");
        }
    }

    @RequestMapping("/queryAnnualById")
    public void queryEmployeeAnnualNumById() {
        Map<String, Integer> annualNum1 = leaveHolidayService.queryEmployeeHolidayNumById(2, 1);
        if (annualNum1.size() < 3) {
            LOGGER.error("员工 2 查询失败！");
        } else {
            LOGGER.info("员工 2 查询成功！\n");
            LOGGER.info("请假数：{}，剩余年假数：{}", annualNum1.get("day_num"), annualNum1.get("annual_num"));
        }
        Map<String, Integer> annualNum2 = leaveHolidayService.queryEmployeeHolidayNumById(4, 1);
        if (annualNum2.size() < 3) {
            LOGGER.error("员工 4 查询失败！");
        } else {
            LOGGER.info("员工 4 查询成功！\n");
            LOGGER.info("请假数：{}，剩余年假数：{}", annualNum2.get("day_num"), annualNum2.get("annual_num"));
        }
    }
}
