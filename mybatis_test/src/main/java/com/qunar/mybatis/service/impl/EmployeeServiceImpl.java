package com.qunar.mybatis.service.impl;

import com.qunar.mybatis.mapper.EmployeeMapper;
import com.qunar.mybatis.model.Employee;
import com.qunar.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeServiceImpl * @Description TODO
 * @Author tako
 * @Date 15:46 2022/8/9
 * @Version 1.0
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Integer insertNewEmployee(Employee employee) {
        Integer cnt = employeeMapper.insertNewEmployee(employee);
        return cnt;
    }

    @Override
    public Employee queryEmployeeDetailById(Integer staffId) {
        Employee employee = employeeMapper.queryEmployeeDetailById(staffId);
        return employee;
    }

    @Override
    public Integer deleteEmployeeByValid(Integer isValid) {
        Integer cnt = employeeMapper.deleteEmployeeByValid(isValid);
        return cnt;
    }

    @Override
    public Integer queryEmployeeCountByName(String name) {
        Integer count = employeeMapper.queryEmployeeCountByName(name);
        return count;
    }

    @Override
    public List<Employee> queryEmployeeByPage(Integer start, Integer size) {
        List<Employee> employees = employeeMapper.queryEmployeeByPage(start, size);
        return employees;
    }

    @Override
    public Integer queryEmployeeIsValid(Integer staffId) {
        Integer isValid = employeeMapper.queryEmployeeIsValid(staffId);
        return isValid;
    }
}
