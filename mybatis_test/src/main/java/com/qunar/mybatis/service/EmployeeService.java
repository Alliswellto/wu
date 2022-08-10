package com.qunar.mybatis.service;

import com.qunar.mybatis.model.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName EmployeeService * @Description TODO
 * @Author tako
 * @Date 14:29 2022/8/9
 * @Version 1.0
 **/
public interface EmployeeService {

    /**
     * 注
     * 1、数据信息弱化了年度信息
     * 2、初始情况下每个员工年假10天，病假10天
     */

    /**
     * 1、保存新入职员工韩梅梅的信息，员工工号为8，女士，其它自定
     * 9、插入10个员工数据(数据自定义)
     * @param employee
     */
    Integer insertNewEmployee(Employee employee);

    /**
     * 2、查询员工工号1的员工信息
     * @param staffId
     * @return
     */
    Employee queryEmployeeDetailById(Integer staffId);

    /**
     * 5、删除已经离职的员工
     * @param isValid
     */
    Integer deleteEmployeeByValid(Integer isValid);

    /**
     * 6、查询姓张的在职员工总数
     * @param name
     * @return
     */
    Integer queryEmployeeCountByName(String name);

    /**
     * 8、分别查询按照工号排名的前1-3名员工和4-6名员工详情(用分页实现)
     */
    List<Employee> queryEmployeeByPage(Integer start, Integer size);

    /**
     * 11、查询是否在职
     */
    Integer queryEmployeeIsValid(Integer staffId);
}
