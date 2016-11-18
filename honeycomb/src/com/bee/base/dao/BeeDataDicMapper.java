package com.bee.base.dao;

import java.util.List;

import com.bee.base.model.BeeDataDic;

public interface BeeDataDicMapper {
    int deleteByPrimaryKey(String id);

    int insert(BeeDataDic record);

    int insertSelective(BeeDataDic record);

    BeeDataDic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BeeDataDic record);

    int updateByPrimaryKey(BeeDataDic record);
    
    List<BeeDataDic> selectListByCondition(BeeDataDic record);
    
    List<BeeDataDic> selectListByDateTypes(String[] arrray);
}