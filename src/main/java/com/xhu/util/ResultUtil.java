package com.xhu.util;

public class ResultUtil {

    public static ResultVo success(Object data) {
        ResultVo result = new ResultVo();
        result.setData(data);
        result.setStatus("success");
        return result;
    }

    public static ResultVo success() {
        return success(null);
    }


    public static ResultVo error(String msg) {
        ResultVo result = new ResultVo();
         result.setStatus("error");
         result.setData(msg);
         return result;
    }
}
