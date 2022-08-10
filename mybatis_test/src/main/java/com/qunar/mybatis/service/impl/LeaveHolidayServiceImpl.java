package com.qunar.mybatis.service.impl;

import com.qunar.mybatis.mapper.LeaveHolidayMapper;
import com.qunar.mybatis.model.Employee;
import com.qunar.mybatis.model.LeaveHoliday;
import com.qunar.mybatis.service.LeaveHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LeaveHolidayServiceImpl * @Description TODO
 * @Author tako
 * @Date 15:48 2022/8/9
 * @Version 1.0
 **/
@Service
public class LeaveHolidayServiceImpl implements LeaveHolidayService {

    @Autowired
    private LeaveHolidayMapper leaveHolidayMapper;

    @Override
    public List<LeaveHoliday> queryEmployeeHolidayDetailById(Integer staffId) {
        List<LeaveHoliday> leaveHolidays = leaveHolidayMapper.queryEmployeeHolidayDetailById(staffId);
        return leaveHolidays;
    }

    @Override
    public List<Long> queryEmployeeAnnualNumLessCount(Integer count) {
        List<Long> cnt = leaveHolidayMapper.queryEmployeeAnnualNumLessCount(count);
        return cnt;
    }

    @Override
    public Map<String, Integer> queryEmployeeHolidayNumById(Integer staffId, Integer type) {
        Map<String, Integer> map = leaveHolidayMapper.queryEmployeeHolidayNumById(staffId, type);
        return map;
    }

    @Override
    public Integer insertLeaveHolidayDetail(LeaveHoliday leaveHoliday) {
        Integer cnt = leaveHolidayMapper.insertLeaveHolidayDetail(leaveHoliday);
        return cnt;
    }
}
