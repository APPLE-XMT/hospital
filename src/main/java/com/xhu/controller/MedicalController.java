package com.xhu.controller;

import com.alibaba.fastjson.JSONObject;
import com.xhu.entity.MedicalRecord;
import com.xhu.mapper.MedicalRecordMapper;
import com.xhu.util.CheckUtil;
import com.xhu.util.ImageUtil;
import com.xhu.util.ResultUtil;
import com.xhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MedicalController {
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    Map<String, Object> maps = new HashMap<>();

    /**
     * 查看病历信息
     *
     * @param session
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public ResultVo findRecord(HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkDoctor(session) == true) {
            int id = (int) session.getAttribute("id");
            List<MedicalRecord> records1 = medicalRecordMapper.findBelong(id);
            JSONObject json1 = new JSONObject();
            json1.put("records1", records1);
            return ResultUtil.success(json1);
        } else if (CheckUtil.checkAdmin(session) == true) {
            List<MedicalRecord> records2 = medicalRecordMapper.findAll();
            JSONObject json2 = new JSONObject();
            json2.put("records2", records2);
            return ResultUtil.success(json2);
        } else {
            return ResultUtil.error("没有操作权限");
        }
    }

    /**
     * 根据名称查找
     *
     * @param recordName
     * @param session
     * @return
     */
    @RequestMapping("/findByName")
    @ResponseBody
    public ResultVo findByName(String recordName, HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkDoctor(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        maps.put("recordName", recordName);
        List<MedicalRecord> name = medicalRecordMapper.findByNature(maps);
        JSONObject json1 = new JSONObject();
        json1.put("records", name);
        maps.clear();
        return ResultUtil.success(json1);
    }

    /**
     * 根据时间范围查找
     *
     * @param startTime
     * @param endTime
     * @param session
     * @return
     */
    @RequestMapping("/findByTime")
    @ResponseBody
    public ResultVo findByTime(@DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime, HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkDoctor(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        maps.put("startTime", startTime);
        maps.put("endTime", endTime);
        List<MedicalRecord> time = medicalRecordMapper.findByNature(maps);
        JSONObject json1 = new JSONObject();
        json1.put("records", time);
        maps.clear();
        return ResultUtil.success(json1);
    }

    /**
     * 录入病历
     *
     * @param medicalRecord
     * @param session
     * @return
     */
    @RequestMapping("/insertRc")
    @ResponseBody
    public ResultVo insertRecord(MedicalRecord medicalRecord, @Nullable MultipartFile picFile, HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkDoctor(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result = 0;
        try {
            //System.out.println(picFile.isEmpty());
            if (picFile != null) {
                String sqlPath = ImageUtil.uploadImage(picFile);
                medicalRecord.setImage(sqlPath);
            }
            result = medicalRecordMapper.insertRc(medicalRecord);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if (result >= 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("系统出错，请重试");
        }
    }

    /**
     * 删除病历
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/delRc")
    @ResponseBody
    public ResultVo delRc(int id, HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkDoctor(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result = 0;
        try {
            result = medicalRecordMapper.delRc(id);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if (result >= 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("系统出错，请重试");
        }
    }

    /**
     * 修改病历
     *
     * @param medicalRecord
     * @param session
     * @return
     */
    @RequestMapping("/modifyRc")
    @ResponseBody
    public ResultVo ModifyRecord(MedicalRecord medicalRecord, @Nullable MultipartFile picFile, HttpSession session) {
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkDoctor(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        int result = 0;
        try {
            if (picFile != null) {
                String sqlPath = ImageUtil.uploadImage(picFile);
                medicalRecord.setImage(sqlPath);
            }
            result = medicalRecordMapper.modifyRc(medicalRecord);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultUtil.error("信息不匹配或系统出错，请重试");
        }
        if (result >= 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("系统出错，请重试");
        }
    }
}
