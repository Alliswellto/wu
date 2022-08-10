package com.qunar.mybatis.controller;

import com.qunar.mybatis.model.LeaveHoliday;
import com.qunar.mybatis.service.EmployeeService;
import com.qunar.mybatis.service.HolidayService;
import com.qunar.mybatis.service.LeaveHolidayService;
import com.qunar.mybatis.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName HolidayProcessController * @Description TODO
 * @Author tako
 * @Date 14:38 2022/8/10
 * @Version 1.0
 **/
@RestController
public class HolidayProcessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HolidayProcessController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private LeaveHolidayService leaveHolidayService;

    /**
     * 员工请假流程
     * 假设员工 1 请 5 天病假
     * @return
     */
    @RequestMapping("/leaveHolidayProcess")
    public void LeaveHolidayProcess() {
        Integer isValid = employeeService.queryEmployeeIsValid(1);
        if (isValid == 2) {
            LOGGER.info("该员工已经离职，病假请求无效！");
            return;
        }
        Integer annualNum = holidayService.queryRemainSickNumById(1);
        if (annualNum < 5) {
            LOGGER.info("该员工剩余病假不足，请求驳回！");
            return;
        }
        Integer updateCnt = holidayService.updateEmployeeSickNumById(1, 5);
        LeaveHoliday leaveHoliday = new LeaveHoliday(1, new Date(),
                DateUtils.dateAdd(new Date(), 5), 5, 2, "北京");
        Integer insertCnt = leaveHolidayService.insertLeaveHolidayDetail(leaveHoliday);
        if (updateCnt > 0 && insertCnt > 0) {
            LOGGER.info("请假成功！");
        } else {
            LOGGER.info("请假失败！");
        }
    }

}
