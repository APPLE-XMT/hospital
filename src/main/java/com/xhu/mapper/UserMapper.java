package com.xhu.mapper;

import com.xhu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserMapper extends Mapper<User> {

    User findOne(User user);

    int modifyPsw(User user);

    User viewInf(int id);

    int infModify(User user);

    int accountDelete(int id);

    List<User> findAll();

    int insertUser(User user);

    int updateState(String card);
}