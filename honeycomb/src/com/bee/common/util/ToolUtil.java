/*
 * Copyright (C), 2015-2016, 蜂巢互联网科技有限公司
 * FileName: ToolUtil.java
 * Author:   huhu
 * Date:     2016年11月7日 下午10:24:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.bee.common.util;

import java.util.UUID;


/**
 * 工具方法类
 *
 * @author huhu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ToolUtil {
	
	/**
	 * 创建uuid
	 *
	 * @return
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}
}