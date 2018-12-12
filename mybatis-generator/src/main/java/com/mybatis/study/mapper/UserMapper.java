package com.mybatis.study.mapper;

import com.mybatis.generator.pojo.QueryExample;
import com.mybatis.study.pojo.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(QueryExample example);

    int deleteByExample(QueryExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    List<User> selectByExample(QueryExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") QueryExample example);
}