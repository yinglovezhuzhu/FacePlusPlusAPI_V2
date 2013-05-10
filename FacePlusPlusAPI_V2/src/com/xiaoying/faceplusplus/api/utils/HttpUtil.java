/*
 * 文件名：HttpUtil.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 功能：网络数据传输工具类
 * @author xiaoying
 */
public class HttpUtil {
	
	public static final String BASE_URL = "https://apicn.faceplusplus.com/v2";

	public static HttpResponse doPost(String actionPath, Map<String, Object> params) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(BASE_URL + actionPath);
		MultipartEntity mulEntity = new MultipartEntity();
		Set<String> keys = params.keySet();
		HttpResponse response = null;
		try {
			for (String key : keys) {
				Object obj = params.get(key);
				if(obj instanceof File) {
					mulEntity.addPart(key, new FileBody((File) obj));
				} else {
					mulEntity.addPart(key, new StringBody(obj.toString()));
				}
			}
			post.setEntity(mulEntity);
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
