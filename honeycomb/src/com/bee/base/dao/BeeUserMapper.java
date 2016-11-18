package com.bee.base.dao;

import java.util.List;

import com.bee.base.model.BeeUser;

public interface BeeUserMapper {
    int deleteByPrimaryKey(String userNo);

    int insert(BeeUser record);

    int insertSelective(BeeUser record);

    BeeUser selectByPrimaryKey(String userNo);

    int updateByPrimaryKeySelective(BeeUser record);

    int updateByPrimaryKey(BeeUser record);
    
    public List<BeeUser> selectListByCondition(BeeUser record);
    
    public String selectMaxUserNo();
}