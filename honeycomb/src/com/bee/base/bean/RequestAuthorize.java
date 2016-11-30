/*
 * Copyright (C), 2015-2016, 蜂巢互联网科技有限公司
 * FileName: RequestAuthorize.java
 * Author:   huhu
 * Date:     2016年11月27日 下午9:31:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.bee.base.bean;

import java.util.List;

import com.bee.base.model.MapRoleMenu;

/**
 * 角色授权请求入参对象
 *
 * @author huhu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RequestAuthorize {

	private String roleNo;
	
	private List<MapRoleMenu> list;

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	public List<MapRoleMenu> getList() {
		return list;
	}

	public void setList(List<MapRoleMenu> list) {
		this.list = list;
	}
	
}
