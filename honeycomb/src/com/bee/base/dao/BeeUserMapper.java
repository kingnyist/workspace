package com.bee.base.dao;

import java.util.List;

import com.bee.base.model.BeeUser;

public interface BeeUserMapper {
	public int deleteByPrimaryKey(String userNo);

	public int insert(BeeUser record);

	public int insertSelective(BeeUser record);

	public BeeUser selectByPrimaryKey(String userNo);

	public int updateByPrimaryKeySelective(BeeUser record);

	public int updateByPrimaryKey(BeeUser record);
    
    public List<BeeUser> selectListByCondition(BeeUser record);
    
    public String selectMaxUserNo();
}