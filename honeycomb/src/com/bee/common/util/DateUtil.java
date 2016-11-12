/*
 * Copyright (C), 2015-2016, 蜂巢互联网科技有限公司
 * FileName: DateUtil.java
 * Author:   huhu
 * Date:     2016年11月7日 下午10:34:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.bee.common.util;

import java.util.Date;

/**
 * 日期方法
 *
 * @author huhu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DateUtil {

	private static final String yyyyMMdd = "yyyy-MM-dd";
	private static final String yyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	private static final String yyyyMMddHHmmss="yyyyMMddHHmmss";
	private static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmss.SSS";
	
	public static Date getNowDate(){
		return new Date();
	}
}
