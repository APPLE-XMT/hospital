package com.xhu.mapper;

import com.xhu.entity.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface RoleMapper extends Mapper<Role> {
    String searchRole(int userId);

    int changeRole(Role role);
}