package com.xhu.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class returnUtil {
    @RequestMapping("/returnIndex")
    public String returnIndex() {
        return "doctor/index";
    }

}
