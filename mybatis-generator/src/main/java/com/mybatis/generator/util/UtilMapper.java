package com.mybatis.generator.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UtilMapper {

	List<Map<String,Object>> select(@Param("sql")String sql);
	
}
