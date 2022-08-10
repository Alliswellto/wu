package com.qunar.mybatis.controller;

import com.qunar.mybatis.model.Employee;
import com.qunar.mybatis.service.EmployeeService;
import com.qunar.mybatis.utils.SnowFlakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName EmployeeController * @Description TODO
 * @Author tako
 * @Date 16:08 2022/8/9
 * @Version 1.0
 **/
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private static final SnowFlakeIdWorker SNOWFLAKE = new SnowFlakeIdWorker(0, 0);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/insert")
    public void insertNewEmployee() {
        Employee employee = new Employee(8L, "韩梅梅",
                "19519533333", "北京", 2, 1);
        Integer cnt = employeeService.insertNewEmployee(employee);
        if (cnt <= 0) {
            LOGGER.error("插入失败！");
        } else {
            LOGGER.info("插入成功");
        }
    }
    
    @RequestMapping("/insertTen")
    public void insertTen() {
        for (int i = 0; i < 10; i ++) {
            Employee employee = new Employee(SNOWFLAKE.nextId(), "zs" + i,
                    "12345678910", "bj" + i, i & 1, i & 1);
            Integer cnt = employeeService.insertNewEmployee(employee);
            if (cnt <= 0) {
                LOGGER.error("插入失败！");
            } else {
                LOGGER.info("插入成功");
            }
        }
    }

    @RequestMapping("/queryDetail")
    public void queryEmployeeDetailById() {
        Employee employee = employeeService.queryEmployeeDetailById(1);
        LOGGER.info("查询成功，employee = {}", employee);
    }

    @RequestMapping("/deleteValid")
    public void deleteEmployeeByValid() {
        Integer cnt = employeeService.deleteEmployeeByValid(2);
        if (cnt <= 0) {
            LOGGER.error("删除失败！");
        } else {
            LOGGER.info("删除成功！");
        }
    }

    @RequestMapping("/queryCountByName")
    public void queryEmployeeCountByName() {
        Integer cnt = employeeService.queryEmployeeCountByName("张");
        if (cnt < 0) {
            LOGGER.error("查询失败！");
        } else {
            LOGGER.info("查询成功，姓张的共有 {} 人", cnt);
        }
    }

    @RequestMapping("queryByPage")
    public void queryEmployeeByPage() {
        // 1 - 3
        List<Employee> employees1 = employeeService.queryEmployeeByPage(0, 3);
        if (employees1 != null && employees1.size() > 0) {
            LOGGER.info("1-3的员工为：");
            employees1.forEach(System.out::println);
        }
        // 4 - 6
        List<Employee> employees2 = employeeService.queryEmployeeByPage(3, 3);
        if (employees2 != null && employees2.size() > 0) {
            LOGGER.info("4-6的员工为：");
            employees2.forEach(System.out::println);
        }
    }

}
