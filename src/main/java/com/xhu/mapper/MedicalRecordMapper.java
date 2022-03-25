package com.xhu.mapper;

import com.xhu.entity.MedicalRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface MedicalRecordMapper extends Mapper<MedicalRecord> {
    List<MedicalRecord> findAll();

    List<MedicalRecord> findBelong(int id);

    int insertRc(MedicalRecord medicalRecord);

    int delRc(int id);

    int  modifyRc(MedicalRecord medicalRecord);

    List<MedicalRecord> findByNature(Map<String,Object> maps);
}