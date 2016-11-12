/*
 * Copyright (C), 2015-2016, 蜂巢互联网科技有限公司
 * FileName: BaseController.java
 * Author:   huhu
 * Date:     2016年11月7日 下午10:11:36
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.bee.base.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * controller父类
 *
 * @author huhu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class BaseController {

	protected String objectToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		String responseMsg = "";
		try {
			responseMsg = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return responseMsg;
	}
}
