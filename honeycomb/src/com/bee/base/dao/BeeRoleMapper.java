package com.bee.base.dao;

import com.bee.base.model.BeeRole;

public interface BeeRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(BeeRole record);

    int insertSelective(BeeRole record);

    BeeRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BeeRole record);

    int updateByPrimaryKey(BeeRole record);
}