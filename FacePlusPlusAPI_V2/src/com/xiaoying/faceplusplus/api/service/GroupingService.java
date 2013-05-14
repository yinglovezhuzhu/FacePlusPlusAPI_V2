/*
 * 文件名：GroupingService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */

package com.xiaoying.faceplusplus.api.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

import com.xiaoying.faceplusplus.api.cliet.Client;
import com.xiaoying.faceplusplus.api.config.Config;
import com.xiaoying.faceplusplus.api.entity.request.grouping.GroupingFaceReq;
import com.xiaoying.faceplusplus.api.entity.response.grouping.GroupingFaceResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：Grouping Face聚类服务类
 * @author xiaoying
 *
 */
public class GroupingService extends BaseService {

	/**
	 * @param client
	 */
	public GroupingService(Client client) {
		super(client);
	}
	
	/**
	 * 人脸聚类
	 * 给出一个Faceset，尝试将其分类，使得来自同一个人的Face被放在同一类中。
	 * Grouping所花费的时间较长, 因此该调用是异步的，仅返回session_id。
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public GroupingFaceResp groupingFace(GroupingFaceReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getFaceset_id()) && StringUtil.isEmpty(body.getFaceset_name())) {
			throw new IllegalArgumentException("faceset_id or faceset_name must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("faceset_id", body.getFaceset_id());
		params.put("faceset_name", body.getFaceset_name());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_GROUPING, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		GroupingFaceResp result = new GroupingFaceResp();
		if(json.containsKey("session_id")) {
			result.setSession_id(json.getString("session_id"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

}
