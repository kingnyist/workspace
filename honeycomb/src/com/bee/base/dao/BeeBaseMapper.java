/*
 * Copyright (C), 2015-2016, 蜂巢互联网科技有限公司
 * FileName: BeeBaseMapper.java
 * Author:   huhu
 * Date:     2016年11月18日 下午6:50:32
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.bee.base.dao;

import java.util.List;

import com.bee.base.bean.PageBean;
import com.bee.base.model.BeeDataDic;

/**
 * DAO父类
 *
 * @author wanglihui
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface BeeBaseMapper<T extends Object> {
	int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(T id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
    
    List<T> selectListByCondition(T record);
}
