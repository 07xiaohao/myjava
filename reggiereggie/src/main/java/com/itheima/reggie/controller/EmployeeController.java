package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.datatype.jsr310.DecimalUtils;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
    /**
     *
     * @param request
     * @param employee
     * @return
     */
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){
        //1、将页面提交的密码password进行md5加密处理
        //2、根据页面提交的用户名username查询数据库
        //3、如果没有查询到则返回登录失败结果
        //4、密码比对，如果不一致则返回登录失败结果
        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        //6、登录成功，将员工id存入Session并返回登录成功结果
        String password = employee.getPassword ();
        if (employee.getPassword()!=null){

            password=DigestUtils.md5DigestAsHex (password.getBytes ());
        }

        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<> ();
        queryWrapper.eq (Employee::getUsername, employee.getUsername ());
        Employee emp = employeeService.getOne (queryWrapper);
        if (emp==null){
            return R.error ("登录失败");
        }
        if (!password.equals (emp.getPassword ())){
            return R.error ("登录失败");
        }
        if (emp.getStatus ()==0){
            return R.error ("账号禁用");
        }
        request.getSession ().setAttribute ("employee", emp.getId ());
        return R.success (emp);
    }
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession ().removeAttribute ("employee");
        return R.success ("退出成功");
    }
    @PostMapping
    public R<String> insert(HttpServletRequest request, @RequestBody Employee employee){
        employee.setPassword (DigestUtils.md5DigestAsHex ("123456".getBytes ()));
        //公共字段自动填充
      /*  employee.setCreateTime (LocalDateTime.now ());
        employee.setUpdateTime (LocalDateTime.now ());
        employee.setCreateUser ((long)request.getSession ().getAttribute ("employee"));
        employee.setUpdateUser ((long)request.getSession ().getAttribute ("employee"));*/

        boolean save = employeeService.save (employee);
        if (save){
            return R.success ("添加员工成功");
        }
        else{
            return R.error ("添加失败");
        }
    }
    @GetMapping("/page")
    public R<Page<Employee>> page(@RequestParam(value = "name",required = false) String name,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize){
        log.info ("page = {},name = {}.pageSize = {}" ,page,name,pageSize);
        //构造分页构造器
        Page page1=new Page (page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> lambdaQueryWrapper=new LambdaQueryWrapper ();
        //添加过滤条件
       lambdaQueryWrapper.like (StringUtils.isNotEmpty (name), Employee::getName, name);
        //添加排序条件
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
       employeeService.page (page1, lambdaQueryWrapper);

        return R.success (page1);
    }
    @PutMapping
    public R<String>  updateStatus(@RequestBody Employee employee,HttpServletRequest request){
        employee.setUpdateUser ((long)request.getSession ().getAttribute ("employee"));
        employee.setUpdateTime (LocalDateTime.now ());
        employeeService.updateById (employee);
        return R.success ("修改信息成功");
    }
    @GetMapping("/{id}")
    public R<Employee> bianji(@PathVariable("id")long id){
        Employee employee = employeeService.getById (id);
        if (employee!=null){
            return R.success (employee);
        }else {
          return   R.error ("没有查询到对应员工信息");
        }
    }
}
