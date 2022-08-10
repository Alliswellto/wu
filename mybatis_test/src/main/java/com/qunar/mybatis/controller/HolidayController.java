package com.qunar.mybatis.controller;

import com.qunar.mybatis.service.HolidayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HolidayController * @Description TODO
 * @Author tako
 * @Date 16:08 2022/8/9
 * @Version 1.0
 **/
@RestController
@RequestMapping("/holiday")
public class HolidayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HolidayController.class);

    private static final int BASE_ANNUAL_NUM = 10;
    private static final int BASE_SICK_NUM = 10;

    @Autowired
    private HolidayService holidayService;

    @RequestMapping("/updateById")
    public void updateEmployeeHolidayById() {
        Integer cnt = holidayService.updateEmployeeHolidayById(3, 3);
        if (cnt <= 0) {
            LOGGER.error("更新失败！");
        } else {
            LOGGER.info("更新成功！");
        }
    }
}
