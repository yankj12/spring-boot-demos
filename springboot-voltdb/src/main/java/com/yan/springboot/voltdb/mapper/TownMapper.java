package com.yan.springboot.voltdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yan.springboot.voltdb.schema.Town;

@Mapper
public interface TownMapper {

	List<Town> findFirstX(Integer first);
}
