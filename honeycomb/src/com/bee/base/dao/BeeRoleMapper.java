package com.bee.base.dao;

import com.bee.base.model.BeeRole;

public interface BeeRoleMapper extends BeeBaseMapper<BeeRole> {
	public String selectMaxId();
}