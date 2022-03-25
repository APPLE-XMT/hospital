package com.xhu.mapper;

import com.xhu.entity.Position;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PositionMapper extends Mapper<Position> {
    List<Position> viewAllPosition();
}