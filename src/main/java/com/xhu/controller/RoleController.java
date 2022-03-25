package com.xhu.controller;

import com.xhu.entity.Role;
import com.xhu.mapper.RoleMapper;
import com.xhu.util.CheckUtil;
import com.xhu.util.ResultUtil;
import com.xhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class RoleController{
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 管理身份
     * @param role
     * @param session
     * @return
     */
    @RequestMapping("/changRole")
    @ResponseBody
    public ResultVo changeRole(Role role, HttpSession session){
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result= 0;
        try {
            result = roleMapper.changeRole(role);
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
