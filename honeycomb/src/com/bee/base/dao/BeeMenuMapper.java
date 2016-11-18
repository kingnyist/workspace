package com.bee.base.dao;

import java.util.List;

import com.bee.base.model.BeeMenu;

public interface BeeMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(BeeMenu record);

    int insertSelective(BeeMenu record);

    BeeMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(BeeMenu record);

    int updateByPrimaryKey(BeeMenu record);
    
    List<BeeMenu> selectListByCondition(BeeMenu record);

    public String selectMaxUserNo();
}