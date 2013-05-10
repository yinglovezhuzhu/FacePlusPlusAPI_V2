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
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xiaoying.faceplusplus.api.client.Client;
import com.xiaoying.faceplusplus.api.service.FaceService;

/**
 * 功能：测试类
 * @author xiaoying
 */
public class Test {
	public static String APP_KEY = "f7644c4bf304dfb8b0afd1935c9ecf2f";
	public static String APP_SECRET = "pyGS__qcKYwn3yuTvWONluk9ciIfvY8A";

	public static void main(String [] args) {
		Client client = new Client(APP_KEY, APP_SECRET);
		FaceService faceService = new FaceService(client);
		try {
//			System.out.println(faceService.detect(new File("/home/xiaoying/pic3.jpg")));
			System.out.println(faceService.detect("http://www.yn.xinhuanet.com/ent/2008-11/11/xin_1331105110914078108772.jpg"));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
