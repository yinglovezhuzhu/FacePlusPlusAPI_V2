/*
 * 文件名：PersonService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-11
 * 修改人：xiaoying
 * 修改时间：2013-5-11
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
import com.xiaoying.faceplusplus.api.entity.Face;
import com.xiaoying.faceplusplus.api.entity.Group;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonAddFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonDeleteReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonGetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonRemoveFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonAddFaceResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonCreateResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonDeleteResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonGetInfoResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonRemoveFaceResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonSetInfoResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：Person相关服务类
 * @author xiaoying
 */
public class PersonService extends BaseService {

	public PersonService(Client client) {
		super(client);
	}
	
	/**
	 * 创建Person
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public PersonCreateResp createPerson(PersonCreateReq body) throws ClientProtocolException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("face_id", body.getFace_id());
		params.put("tag", body.getTag());
		params.put("group_id", body.getGroup_id());
		params.put("group_name", body.getGroup_name());
		return getCreateRespose(params);
	}
	
	private PersonCreateResp getCreateRespose(Map<String, Object> params) throws ClientProtocolException, IOException {
		HttpResponse resp = HttpUtil.doPost(Config.PATH_PERSON_CREATE, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonCreateResp result = new PersonCreateResp();
		if(json.containsKey("added_group")) {
			result.setAdded_group(json.getInt("added_group"));
			result.setAdded_face(json.getInt("added_face"));
			result.setTag(json.getString("tag"));
			result.setPerson_name(json.getString("person_name"));
			result.setPerson_id(json.getString("person_id"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	/**
	 * 删除Person
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public PersonDeleteResp deletePerson(PersonDeleteReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_PERSON_DELETE, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonDeleteResp result = new PersonDeleteResp();
		if(json.containsKey("deleted")) {
			result.setDeleted(json.getInt("deleted"));
			result.setSuccess(json.getBoolean("success"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	/**
	 * 向Person中添加Face
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public PersonAddFaceResp addFace(PersonAddFaceReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		if(StringUtil.isEmpty(body.getFace_id())) {
			throw new IllegalArgumentException("face_id must be set");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		params.put("face_id", body.getFace_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_PERSON_ADD_FACE, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonAddFaceResp result = new PersonAddFaceResp();
		if(json.containsKey("added")) {
			result.setAdded(json.getInt("added"));
			result.setSuccess(json.getBoolean("success"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	/**
	 * 从Person中删除Face
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public PersonRemoveFaceResp removeFace(PersonRemoveFaceReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		if(StringUtil.isEmpty(body.getFace_id())) {
			throw new IllegalArgumentException("face_id must be set");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		params.put("face_id", body.getFace_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_PERSON_REMOVE_FACE, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonRemoveFaceResp result = new PersonRemoveFaceResp();
		if(json.containsKey("removed")) {
			result.setRemoved(json.getInt("removed"));
			result.setSuccess(json.getBoolean("success"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	/**
	 * 修改Person信息
	 * @param body
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public PersonSetInfoResp setPersonInfo(PersonSetInfoReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		params.put("name", body.getName());
		params.put("tag", body.getTag());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_PERSON_SET_INFO, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonSetInfoResp result = new PersonSetInfoResp();
		if(json.containsKey("person_id")) {
			result.setPerson_id(json.getString("person_id"));
			result.setPerson_name(json.getString("person_name"));
			result.setTag(json.getString("tag"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	/**
	 * 查询Person的信息
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public PersonGetInfoResp getPersonInfo(PersonGetInfoReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getPerson_id()) && StringUtil.isEmpty(body.getPerson_name())) {
			throw new IllegalArgumentException("person_name or person_id must be to set one");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("person_name", body.getPerson_name());
		params.put("person_id", body.getPerson_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_PERSON_GET_INFO, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		PersonGetInfoResp result = new PersonGetInfoResp();
		if(json.containsKey("person_id")) {
			result.setPerson_id(json.getString("person_id"));
			result.setPerson_name(json.getString("person_name"));
			result.setFace(getFaceInfo(json.getJSONArray("face")));
			result.setGroup(getGroupInfo(json.getJSONArray("group")));
			result.setTag(json.getString("tag"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	private List<Face> getFaceInfo(JSONArray faceArray) {
		List<Face> faces = new ArrayList<Face>();
		JSONObject faceObj = null;
		Face face = null;
		for(Iterator<JSONObject> i = faceArray.iterator(); i.hasNext(); ) {
			faceObj = JSONObject.fromObject(i.next());
			face = new Face();
			face.setFace_id(faceObj.getString("face_id"));
			face.setTag(faceObj.getString("tag"));
			faces.add(face);
		}
		return faces;
	}
	
	@SuppressWarnings("unchecked")
	private List<Group> getGroupInfo(JSONArray groupArray) {
		List<Group> groups = new ArrayList<Group>();
		JSONObject groupObj = null;
		Group group = null;
		for(Iterator<JSONObject> i = groupArray.iterator(); i.hasNext(); ) {
			groupObj = JSONObject.fromObject(i.next());
			group = new Group();
			group.setGroup_id(groupObj.getString("group_id"));
			group.setGroup_name(groupObj.getString("group_name"));
			group.setTag(groupObj.getString("tag"));
			groups.add(group);
		}
		return groups;
	}
	
}
