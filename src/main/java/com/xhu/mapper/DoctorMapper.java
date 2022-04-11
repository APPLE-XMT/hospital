package com.xhu.mapper;

import com.xhu.entity.Doctor;
import com.xhu.util.docVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

@Repository
public interface DoctorMapper extends Mapper<Doctor> {
    List<Doctor> viewDoctor();

    int modify(docVo vo);

    int deleteDoc(int id);

    int addDoc(docVo vo);

    List<Doctor> oneDoctor(Map<String,Object> map);
}