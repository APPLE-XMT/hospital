package com.xhu.controller;

import com.alibaba.fastjson.JSONObject;
import com.xhu.entity.Doctor;
import com.xhu.mapper.DoctorMapper;
import com.xhu.util.CheckUtil;
import com.xhu.util.ResultUtil;
import com.xhu.util.ResultVo;
import com.xhu.util.docVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.Doc;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DoctorController {
    @Autowired
    private DoctorMapper doctorMapper;

    /**
     * 查询所有医生
     * @param session
     * @return
     */
    @RequestMapping("/viewDoc")
    @ResponseBody
    public ResultVo viewDoctor(HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        List<Doctor> doctors = doctorMapper.viewDoctor();
        JSONObject docJson = new JSONObject();
        docJson.put("docs", doctors);
        return ResultUtil.success(docJson);
    }


    /**
     * 修改医生信息
     * @param vo
     * @param session
     * @return
     */
    @RequestMapping("/modifyDoc")
    @ResponseBody
    public ResultVo modifyDoctor(docVo vo, HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result= 0;
        try {
            result = doctorMapper.modify(vo);
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
     * 删除医生信息
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/delDoc")
    @ResponseBody
    public ResultVo delDoctor(int id,HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result= 0;
        try {
            result = doctorMapper.deleteDoc(id);
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
     * 录入医生信息
     * @param doctor
     * @param session
     * @return
     */
    @RequestMapping("/addDoc")
    @ResponseBody
    public ResultVo addDoctor(docVo vo,HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result= 0;
        try {
            result = doctorMapper.addDoc(vo);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("系统出错，请重试！");
        }
        if (result >= 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("系统出错，请重试！");
        }
    }

    /**
     * 根据医生姓名和科室查询
     * @param doctorName
     * @param depName
     * @param session
     * @return
     */
    @RequestMapping("findDoc")
    @ResponseBody
    public ResultVo findDoc(String doctorName,String depName, HttpSession session){

        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("doctorName",doctorName);
        map.put("depName",depName);
        List<Doctor> doctors=doctorMapper.oneDoctor(map);
        if(doctors.isEmpty()){
            return ResultUtil.error("医生不存在");
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("doctors",doctors);
        return ResultUtil.success(jsonObject);
    }
}
