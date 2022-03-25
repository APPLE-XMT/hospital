package com.xhu.controller;

import com.alibaba.fastjson.JSONObject;
import com.xhu.entity.Position;
import com.xhu.mapper.PositionMapper;
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
public class PositionController {
    @Autowired
    private PositionMapper positionMapper;

    @RequestMapping("/viewPos")
    @ResponseBody
    public ResultVo viewPosition(HttpSession session){
        if (CheckUtil.checkLogin(session) == false) {
            return ResultUtil.error("请登录后操作");
        }
        if (CheckUtil.checkAdmin(session) == false) {
            return ResultUtil.error("没有操作权限");
        }
        List<Position> positions=positionMapper.viewAllPosition();
        JSONObject posJson=new JSONObject();
        posJson.put("positions",positions);
        return ResultUtil.success(posJson);
    }
}
