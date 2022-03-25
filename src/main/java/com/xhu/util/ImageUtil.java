package com.xhu.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public static String uploadImage(MultipartFile pictureFile){
        if(pictureFile.isEmpty()){
            return "文件上传失败";
        }
        String fileName=pictureFile.getOriginalFilename();
        System.out.println(fileName);
        //文件路径
//        String path="../src/main/webapp/WEB-INF/image/";
//        String path=System.getProperty("user.dir")+System.getProperty("file.separator")+"img";
        String path = System.getProperty("user.dir")+System.getProperty("file.separator")+"image"
                +System.getProperty("file.separator")+"patientImage";
        System.out.println(path);
        File file=new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        File realPath=new File(path+System.getProperty("file.separator")+fileName);
        String sqlPath = "/image/patientImage/"+fileName;
        try {
            pictureFile.transferTo(realPath);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return sqlPath;
    }
}