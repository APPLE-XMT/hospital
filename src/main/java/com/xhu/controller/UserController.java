package com.xhu.controller;

import com.alibaba.fastjson.JSONObject;
import com.xhu.entity.User;
import com.xhu.mapper.UserMapper;
import com.xhu.util.CheckUtil;
import com.xhu.util.ResultUtil;
import com.xhu.util.ResultVo;
import com.xhu.util.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /*private String username;*/


    /*public ResultVo checkLogin(HttpSession session){
        username = (String) session.getAttribute("username");
        if (username == null) {
            return ResultUtil.error("请登录后操作");
        }
        return ResultUtil.success();
    }*/


    /**
     * 检查邮箱验证
     *
     * @param email
     * @return
     */
    public Map<String, Object> checkMail(String email) {
        String number = "";
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            number += new Random().nextInt(10);
        }
        try {
            SendMail.sendMail(email, number);
        } catch (Exception exception) {
            exception.printStackTrace();
            map.put("flag", "false");
            return map;
        }
        map.put("flag", "true");
        map.put("number", number);
        return map;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResultVo register(User user) {
        /*User userResult = userMapper.findOne(user);
        if (userResult != null) {
            return ResultUtil.error("用户名已存在");
        }*/
        Map<String, Object> map = checkMail(user.getEmail());
        if (map.get("flag") == "false") {
            return ResultUtil.error("邮件发送失败");
        }
        JSONObject jsonObject = new JSONObject();
        int result = 0;
        try {
            if (map.get("flag") == "true"){
                String number = (String) map.get("number");
                jsonObject.put("number", number);
                jsonObject.put("card", user.getCard());
                result = userMapper.insertUser(user);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("系统出错，请重试！");
        }
        if (result >= 1) {
            return ResultUtil.success(jsonObject);
        } else {
            return ResultUtil.error("系统出错，请重试！");
        }
    }

    /**
     * 激活用户
     *
     * @param card
     * @return
     */
    @RequestMapping("/active")
    @ResponseBody
    public ResultVo activeUser(String card) {
        try {
            int result = userMapper.updateState(card);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("注册失败，请重试");
        }
        return ResultUtil.success("注册成功");
    }


    /**
     * 用户登录
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultVo userLogin(User user, HttpSession session) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return ResultUtil.error("用户名或密码为空");
        }
        User userResult = userMapper.findOne(user);
        if (userResult == null) {
            return ResultUtil.error("用户不存在");
        }
        session.setAttribute("username", userResult.getUsername());
        session.setAttribute("password", userResult.getPassword());
        session.setAttribute("id", userResult.getId());
        return ResultUtil.success();
    }

    /**
     * 修改密码
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/modifyPsw")
    @ResponseBody
    public ResultVo modifyPsw(String oldPsw, User user, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResultUtil.error("请登录后操作");
        }
        if (user.getPassword() == null) {
            return ResultUtil.error("密码为空");
        }
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(oldPsw);
        if (userMapper.findOne(user1) == null) {
            return ResultUtil.error("用户输入密码错误");
        }
        user.setUsername(username);
        int result = 0;
        try {
            result = userMapper.modifyPsw(user);
        } catch (Exception exception) {
            exception.printStackTrace();
            //System.out.println("sql出错");
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if (result >= 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("系统出错，请重试！");
        }
    }

    /**
     * 查看所有用户
     *
     * @param session
     * @return
     */
    @RequestMapping("/allUser")
    @ResponseBody
    public ResultVo findAllUser(HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        List<User> users = userMapper.findAll();
        JSONObject userJson = new JSONObject();
        userJson.put("users", users);
        return ResultUtil.success(userJson);
    }

    /**
     * 用户个人信息展示
     *
     * @param session
     * @return
     */
    @RequestMapping("/userInf")
    @ResponseBody
    public ResultVo userInf(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResultUtil.error("请登录后操作");
        }
        User user = userMapper.viewInf((int) session.getAttribute("id"));
        JSONObject userJson = new JSONObject();
        userJson.put("users", user);
        return ResultUtil.success(userJson);
    }

    /**
     * 用户信息修改
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/infModify")
    @ResponseBody
    public ResultVo infModify(User user, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResultUtil.error("请登录后操作");
        }
        int id = (int) session.getAttribute("id");
        user.setId(id);
        int result = 0;
        try {
            result = userMapper.infModify(user);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if (result >= 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("系统出错，请重试！");
        }
    }

    /**
     * 查看个人消息
     * @param session
     * @return
     */
    /*@RequestMapping("/viewMsg")
    @ResponseBody
    public ResultVo viewMsg(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResultUtil.error("请登录后操作");
        }
        String msg = userMapper.viewInf((int) session.getAttribute("id")).getMessage();
        return ResultUtil.success(msg);
    }*/

    /**
     * 账号注销
     *
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/accountDel")
    @ResponseBody
    public ResultVo accountDelete(String password, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String acc_psw = (String) session.getAttribute("password");
        int id = (int) session.getAttribute("id");
        if (username == null) {
            return ResultUtil.error("请登录后操作");
        }
        if (acc_psw.equals(password)) {
            int result = 0;
            try {
                result = userMapper.accountDelete(id);
            } catch (Exception exception) {
                exception.printStackTrace();
                return ResultUtil.error("信息不匹配或系统出错，请重试");
            }
            if (result >= 1) {
                return ResultUtil.success();
            } else {
                return ResultUtil.error("系统出错,请重试");
            }
        } else {
            return ResultUtil.error("密码错误");
        }
    }
}
