package com.xhu.controller;

import com.alibaba.fastjson.JSONObject;
import com.xhu.entity.Patient;
import com.xhu.mapper.PatientMapper;
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
public class PatientController {
    @Autowired
    private PatientMapper patientMapper;

    /**
     * 管理员查询所有患者
     * @param session
     * @return
     */
    @RequestMapping("/viewPatients")
    @ResponseBody
    public ResultVo viewPatients(HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        List<Patient> patients=patientMapper.viewPatients();
        JSONObject patientJson = new JSONObject();
        patientJson.put("patients", patients);
        return ResultUtil.success(patientJson);
    }

    /**
     * 医生查询名下患者
     * @param session
     * @return
     */
    @RequestMapping("/findBelong")
    @ResponseBody
    public ResultVo find(HttpSession session) {
        int id=(int)session.getAttribute("id");
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkDoctor(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        List<Patient> patients=patientMapper.findBelong(id);
        JSONObject patientJson = new JSONObject();
        patientJson.put("patients", patients);
        return ResultUtil.success(patientJson);
    }

    /**
     * 修改患者信息
     * @param patient
     * @param session
     * @return
     */
    @RequestMapping("/modifyPa")
    @ResponseBody
    public ResultVo modifyPatient(Patient patient,HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkDoctor(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result= 0;
        try {
            result = patientMapper.modify(patient);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if(result>=1){
            return ResultUtil.success();
        }else {
            return ResultUtil.error("系统出错，请重试");
        }
    }

    @RequestMapping("/insertPa")
    @ResponseBody
    public ResultVo insertPa(Patient patient,HttpSession session){
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkDoctor(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result= 0;
        try {
            result = patientMapper.insertPa(patient);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if(result>=1){
            return ResultUtil.success();
        }else {
            return ResultUtil.error("系统出错，请重试");
        }
    }
}
