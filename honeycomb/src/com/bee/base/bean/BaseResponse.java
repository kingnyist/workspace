package com.bee.base.bean;

import java.util.ArrayList;
import java.util.List;

import com.bee.base.model.BeeUser;

public class BaseResponse {

	private Integer total;
	private List<BeeUser> rows =  new ArrayList<BeeUser>();
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<BeeUser> getRows() {
		return rows;
	}
	public void setRows(List<BeeUser> rows) {
		this.rows = rows;
	}
	
}
