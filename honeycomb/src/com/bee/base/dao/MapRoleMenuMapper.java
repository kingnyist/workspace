package com.bee.base.dao;

import java.util.List;

import com.bee.base.model.BeeUser;
import com.bee.base.model.MapRoleMenu;

public interface MapRoleMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(MapRoleMenu record);

    int insertSelective(MapRoleMenu record);

    MapRoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MapRoleMenu record);

    int updateByPrimaryKeyWithBLOBs(MapRoleMenu record);

    int updateByPrimaryKey(MapRoleMenu record);
    
    int deleteBySelectivity(MapRoleMenu record);
    
    public List<MapRoleMenu> selectListByCondition(MapRoleMenu record);
}