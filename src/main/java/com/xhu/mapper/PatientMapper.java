package com.xhu.mapper;

import com.xhu.entity.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PatientMapper extends Mapper<Patient> {
    List<Patient> viewPatients();

    List<Patient> findBelong(int id);

    int modify(Patient patient);

    int insertPa(Patient patient);
}