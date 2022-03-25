package com.xhu.controller;

import com.alibaba.fastjson.JSONObject;
import com.xhu.entity.Department;
import com.xhu.mapper.DepartmentMapper;
import com.xhu.util.CheckUtil;
import com.xhu.util.ResultUtil;
import com.xhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DepController {

    @Autowired
    private DepartmentMapper departmentMapper;

/*    @Autowired
    private RoleMapper roleMapper;

    *//**
     * 检查是否登录到系统
     * @param session
     * @return
     *//*
    public boolean checkLogin(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return false;
        } else {
            return true;
        }
    }

    *//**
     * 检查用户角色，给予操作权限
     * @param session
     * @return
     *//*
    public boolean check(HttpSession session) {
        int id = (int) session.getAttribute("id");
        String role = roleMapper.searchRole(id);
        if ("管理员".equals(role)) {
            return true;
        } else {
            return false;
        }
    }*/

    /**
     * 增加科室
     * @param session
     * @param department
     * @return
     */
    @RequestMapping("/addDep")
    @ResponseBody
    public ResultVo addDep(HttpSession session, Department department) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result = 0;
        try {
            result = departmentMapper.addDep(department);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if (result >= 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("系统出错,请重试");
        }
    }

    /**
     * 删除科室
     * @param session
     * @return
     */
    @RequestMapping("/delDep")
    @ResponseBody
    public ResultVo delDep(String departmentName,HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result = 0;
        try {
            result = departmentMapper.delDep(departmentName);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if (result >= 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("系统出错,请重试");
        }
    }

    /**
     * 查询某个科室
     * @param departmentName
     * @param session
     * @return
     */
    @RequestMapping("/selectDep")
    @ResponseBody
    public ResultVo selectDep(String departmentName,HttpSession session){
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        Department department=departmentMapper.searchOne(departmentName);
        JSONObject depJson=new JSONObject();
        depJson.put("dep",department);
        return ResultUtil.success(depJson);
    }

    /**
     * 查询所有科室
     * @param session
     * @return
     */
    @RequestMapping("/selectDeps")
    @ResponseBody
    public ResultVo selectDeps(HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        List<Department> departments=departmentMapper.selectAll();
        JSONObject depJson=new JSONObject();
        depJson.put("deps",departments);
        return ResultUtil.success(depJson);
    }

    /*@RequestMapping("/getDep")
    @ResponseBody
    public ResultVo getDep(String depName,HttpSession session) {
        if (checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (check(session) == false) {
            return ResultUtil.error("没有操作权限");
        }

    }*/

    /**
     * 修改科室信息
     * @param department
     * @param session
     * @return
     */
    @RequestMapping("/modifyDep")
    @ResponseBody
    public ResultVo modifyDep(Department department,int id,HttpSession session){
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        department.setId(id);
        int result= 0;
        try {
            result = departmentMapper.modifyDep(department);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if (result >= 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("系统出错,请重试");
        }
    }
}
