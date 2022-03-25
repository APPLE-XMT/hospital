package com.xhu.mapper;

import com.xhu.entity.Department;
import com.xhu.entity.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DepartmentMapper extends Mapper<Department> {
    int addDep(Department department);

    int delDep(String DepName);

    List<Department> selectAll();

    Department searchOne(String DepName);

    int modifyDep(Department department);
}