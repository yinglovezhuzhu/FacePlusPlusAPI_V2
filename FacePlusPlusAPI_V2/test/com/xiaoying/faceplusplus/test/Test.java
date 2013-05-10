/*
 * 文件名：Test.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.xiaoying.faceplusplus.api.utils.HttpUtil;

/**
 * 功能：测试类
 * @author xiaoying
 */
public class Test {
	public static String APP_KEY = "f7644c4bf304dfb8b0afd1935c9ecf2f";
	public static String APP_SECRET = "pyGS__qcKYwn3yuTvWONluk9ciIfvY8A";

	public static void main(String [] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("img", new File("/home/xiaoying/pic9.jpg"));
		params.put("api_key", APP_KEY);
		params.put("api_secret", APP_SECRET);
		HttpUtil.doPost("/detection/detect", params);
	}
}
