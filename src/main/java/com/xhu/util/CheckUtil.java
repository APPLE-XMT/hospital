package com.xhu.util;

import com.xhu.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

public class CheckUtil {
    private static RoleMapper roleMapper;

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper){
        CheckUtil.roleMapper=roleMapper;
    }

    /**
     * 检查是否登录到系统
     * @param session
     * @return
     */
    public static boolean checkLogin(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检查用户角色，给予操作权限
     * @param session
     * @return
     */
    public static boolean checkAdmin(HttpSession session) {
        int id = (int) session.getAttribute("id");
        String role = roleMapper.searchRole(id);
        if ("管理员".equals(role)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查是否为医生
     * @param session
     * @return
     */
    public static boolean checkDoctor(HttpSession session) {
        int id = (int) session.getAttribute("id");
        String role = roleMapper.searchRole(id);
        if ("医生".equals(role)) {
            return true;
        } else {
            return false;
        }
    }
}
