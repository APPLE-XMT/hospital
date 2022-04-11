package com.xhu.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class returnUtil {
    @RequestMapping("/returnDoctorIndex")
    public String returnDoctorIndex() {
        return "doctor/index";
    }

    @RequestMapping("/returnManagerIndex")
    public String returnManagerIndex() {
        return "manager/index";
    }

    @RequestMapping("/returnLogin")
    public String returnLogin() {
        return "login";
    }

}
