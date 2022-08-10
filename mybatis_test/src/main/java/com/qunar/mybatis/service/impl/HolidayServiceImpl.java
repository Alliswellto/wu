package com.qunar.mybatis.service.impl;

import com.qunar.mybatis.mapper.HolidayMapper;
import com.qunar.mybatis.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName HolidayService * @Description TODO
 * @Author tako
 * @Date 15:47 2022/8/9
 * @Version 1.0
 **/
@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayMapper holidayMapper;

    @Override
    public Integer updateEmployeeHolidayById(Integer staffId, Integer annualNum) {
        Integer cnt = holidayMapper.updateEmployeeHolidayById(staffId, annualNum);
        return cnt;
    }

    @Override
    public Integer queryRemainSickNumById(Integer staffId) {
        Integer sickNum = holidayMapper.queryRemainSickNumById(staffId);
        return sickNum;
    }

    @Override
    public Integer updateEmployeeSickNumById(Integer staffId, Integer cnt) {
        Integer count = holidayMapper.updateEmployeeSickNumById(staffId, cnt);
        return count;
    }
}
