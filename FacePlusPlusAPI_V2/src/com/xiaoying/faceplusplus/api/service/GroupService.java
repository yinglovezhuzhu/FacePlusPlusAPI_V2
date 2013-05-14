/*
 * 文件名：GroupService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-13
 * 修改人：xiaoying
 * 修改时间：2013-5-13
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

import com.xiaoying.faceplusplus.api.cliet.Client;
import com.xiaoying.faceplusplus.api.config.Config;
import com.xiaoying.faceplusplus.api.entity.Person;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupAddPersonReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupDeleteReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupGetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupRemovePersonReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupAddPersonResp;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupCreateResp;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupDeleteResp;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupGetInfoResp;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupRemovePersonResp;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupSetInfoResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：
 * @author xiaoying
 */
public class GroupService extends BaseService {

	public GroupService(Client client) {
		super(client);
	}

	/**
	 * 创建Group
	 * @param body
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public GroupCreateResp createGroup(GroupCreateReq body) throws ClientProtocolException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("group_name", body.getGroup_name());
		params.put("tag", body.getTag());
		params.put("person_id", body.getPerson_id());
		params.put("person_name", body.getPerson_name());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_GROUP_CREATE, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		GroupCreateResp result = new GroupCreateResp();
		if(json.containsKey("group_id")) {
			result.setGroup_id(json.getString("group_id"));
			result.setGroup_name(json.getString("group_name"));
			result.setTag(json.getString("tag"));
			result.setAdded_person(json.getInt("added_person"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	/**
	 * 删除Group
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public GroupDeleteResp deleteGroup(GroupDeleteReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getGroup_id()) || StringUtil.isEmpty(body.getGroup_name())) {
			throw new IllegalArgumentException("group_id or group_name must to be set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("group_name", body.getGroup_name());
		params.put("group_id", body.getGroup_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_GROUP_DELETE, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		GroupDeleteResp result = new GroupDeleteResp();
		if(json.containsKey("deleted")) {
			result.setSuccess(json.getBoolean("success"));
			result.setDeleted(json.getInt("deleted"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	/**
	 * 添加Person
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public GroupAddPersonResp addPerson(GroupAddPersonReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getGroup_id()) && StringUtil.isEmpty(body.getGroup_name())) {
			throw new IllegalArgumentException("group_id or group_name must to be set one");
		}
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_id or person_name must to be set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("group_name", body.getGroup_name());
		params.put("group_id", body.getGroup_id());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_GROUP_ADD_PERSON, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		GroupAddPersonResp result = new GroupAddPersonResp();
		if(json.containsKey("added")) {
			result.setSuccess(json.getBoolean("success"));
			result.setAdded(json.getInt("added"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	/**
	 * 从Group中删除Person
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public GroupRemovePersonResp removePerson(GroupRemovePersonReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getGroup_id()) && StringUtil.isEmpty(body.getGroup_name())) {
			throw new IllegalArgumentException("group_id or group_name must to be set one");
		}
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_id or person_name must to be set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("group_name", body.getGroup_name());
		params.put("group_id", body.getGroup_id());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_GROUP_ADD_PERSON, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		GroupRemovePersonResp result = new GroupRemovePersonResp();
		if(json.containsKey("removed")) {
			result.setSuccess(json.getBoolean("success"));
			result.setRemoved(json.getInt("removed"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	/**
	 * 设置Group的信息
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public GroupSetInfoResp setGroupInfo(GroupSetInfoReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getGroup_id()) && StringUtil.isEmpty(body.getGroup_name())) {
			throw new IllegalArgumentException("group_id or group_name must to be set one");
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("group_name", body.getGroup_name());
		params.put("group_id", body.getGroup_id());
		params.put("name", body.getName());
		params.put("tag", body.getTag());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_GROUP_SET_INFO, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		GroupSetInfoResp result = new GroupSetInfoResp();
		if(json.containsKey("group_name")) {
			result.setGroup_name(json.getString("group_name"));
			result.setGroup_id(json.getString("group_id"));
			result.setTag(json.getString("tag"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	public GroupGetInfoResp getGroupInfo(GroupGetInfoReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getGroup_id()) && StringUtil.isEmpty(body.getGroup_name())) {
			throw new IllegalArgumentException("group_id or group_name must to be set one");
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("group_name", body.getGroup_name());
		params.put("group_id", body.getGroup_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_GROUP_GET_INFO, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		GroupGetInfoResp result = new GroupGetInfoResp();
		if(json.containsKey("group_name")) {
			result.setGroup_name(json.getString("group_name"));
			result.setGroup_id(json.getString("group_id"));
			result.setTag(json.getString("tag"));
			result.setPerson(getPersons(json.getJSONArray("person")));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private List<Person> getPersons(JSONArray personArray) {
		List<Person> persons = new ArrayList<Person>();
		Person person = null;
		JSONObject personObj = null;
		for(Iterator<JSONObject> i = personArray.iterator(); i.hasNext(); ) {
			personObj = JSONObject.fromObject(i.next());
			person = new Person();
			person.setPerson_name(personObj.getString("person_name"));
			person.setPerson_id(personObj.getString("person_id"));
			person.setTag(personObj.getString("tag"));
			persons.add(person);
		}
		return persons;
	}
	
}
