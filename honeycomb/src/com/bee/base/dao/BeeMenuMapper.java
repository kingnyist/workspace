package com.bee.base.dao;

import com.bee.base.model.BeeMenu;

public interface BeeMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(BeeMenu record);

    int insertSelective(BeeMenu record);

    BeeMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(BeeMenu record);

    int updateByPrimaryKey(BeeMenu record);
}