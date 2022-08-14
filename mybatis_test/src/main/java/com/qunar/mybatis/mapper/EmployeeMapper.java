package com.qunar.mybatis.mapper;

import com.qunar.mybatis.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName Employee * @Description TODO
 * @Author tako
 * @Date 14:29 2022/8/9
 * @Version 1.0
 **/
@Mapper
public interface EmployeeMapper {

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
//    @Insert(value = "insert into employee(staff_id, name, mobile, area, gender, is_valid) " +
//            "values(#{staffId}, #{name}, #{mobile}, #{area}, #{gender}, #{isValid})")
    Integer insertNewEmployee(Employee employee);

    /**
     * 2、查询员工工号1的员工信息
     * @param staffId
     * @return
     */
//    @Select(value = "select id, staff_id, name, mobile, area, gender, is_valid from employee " +
//            "where staff_id = #{staffId}")
    Employee queryEmployeeDetailById(@Param("staffId") Integer staffId);

    /**
     * 5、删除已经离职的员工
     * @param isValid
     */
//    @Delete(value = "delete from employee where is_valid = #{isValid}")
    Integer deleteEmployeeByValid(@Param("isValid") Integer isValid);

    /**
     * 6、查询姓张的在职员工总数
     * @param name
     * @return
     */
//    @Select(value = "select count(name) cnt from employee where name like #{name}\"%\"")
    Integer queryEmployeeCountByName(@Param("name") String name);

    /**
     * 8、分别查询按照工号排名的前1-3名员工和4-6名员工详情(用分页实现)
     *    @Param("start") Integer start, @Param("size") Integer size
     */
//    @Select(value = "select id, staff_id, name, mobile, area, gender, is_valid from employee " +
//            "order by staff_id limit #{start}, #{size}")
    List<Employee> queryEmployeeByPage();

    /**
     * 11、一个员工要请病假，请写出具体逻辑实现
     *    - 查询是否在职
     *    - 查询剩余病假是否充足
     *    - 若充足则更新 holiday 表的剩余年假数及 leave_holiday 的请假详情
     */
//    @Select(value = "select is_valid from employee where staff_id = #{staffId}")
    Integer queryEmployeeIsValid(@Param("staffId") Integer staffId);
}
