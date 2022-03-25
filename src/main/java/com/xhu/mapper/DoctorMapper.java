package com.xhu.mapper;

import com.xhu.entity.Doctor;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DoctorMapper extends Mapper<Doctor> {
    List<Doctor> viewDoctor();

    int modify(Doctor doctor);

    int deleteDoc(int id);

    int addDoc(Doctor doctor);
}