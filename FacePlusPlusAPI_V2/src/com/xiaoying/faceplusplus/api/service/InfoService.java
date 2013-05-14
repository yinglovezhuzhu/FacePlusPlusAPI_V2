/*
 * 文件名：InfoService.java
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
import com.xiaoying.faceplusplus.api.entity.Faceset;
import com.xiaoying.faceplusplus.api.entity.Group;
import com.xiaoying.faceplusplus.api.entity.Person;
import com.xiaoying.faceplusplus.api.entity.PointF;
import com.xiaoying.faceplusplus.api.entity.request.info.InfoGetFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.info.InfoGetImageReq;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetFaceResp;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetFaceResp.FaceInfo;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetAppResp;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetFacesetListResp;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetGroupListResp;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetImageResp;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetPersonListResp;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetQuotaResp;
import com.xiaoying.faceplusplus.api.entity.response.info.InfoGetSessionResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：查询信息的服务类
 * @author xiaoying
 * 
 */
public class InfoService extends BaseService {

	/**
	 * @param client
	 */
	public InfoService(Client client) {
		super(client);
	}
	
	/**
	 * 获取一张image的信息, 包括其中包含的face等信息
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InfoGetImageResp getImage(InfoGetImageReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getImg_id())) {
			throw new IllegalArgumentException("img_id should not be null");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("img_id", body.getImg_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_INFO_GET_IMAGE, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		InfoGetImageResp result = new InfoGetImageResp();
		if(json.containsKey("img_id")) {
			result.setImg_id(json.getString("img_id"));
			result.setUrl(json.getString("url"));
			result.setFace(getFaces(json.getJSONArray("face")));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	/**
	 * 给定一组Face，返回相应的信息(包括源图片, 相关的person等等)。
	 * @param body
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InfoGetFaceResp getFace(InfoGetFaceReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getFace_id())) {
			throw new IllegalArgumentException("img_id should not be null");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("face_id", body.getFace_id());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_INFO_GET_FACE, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		InfoGetFaceResp result = new InfoGetFaceResp();
		if(json.containsKey("face_info")) {
			result.setFace_info(getFaceInfo(json.getJSONArray("face_info")));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	/**
	 * 返回该App中的所有Person
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InfoGetPersonListResp getPersonList() throws ClientProtocolException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_INFO_GET_PERSON_LIST, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		InfoGetPersonListResp result = new InfoGetPersonListResp();
		if(json.containsKey("person")) {
			result.setPerson(getPersons(json.getJSONArray("person")));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	/**
	 * 返回该App中的所有faceset
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InfoGetFacesetListResp getFacsetList() throws ClientProtocolException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_INFO_GET_FACESET_LIST, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		InfoGetFacesetListResp result = new InfoGetFacesetListResp();
		if(json.containsKey("faceset")) {
			result.setFaceset(getFacesets(json.getJSONArray("faceset")));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	/**
	 * 返回这个App中的所有Group
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InfoGetGroupListResp getGroupList() throws ClientProtocolException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_INFO_GET_GROUP_LIST, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		InfoGetGroupListResp result = new InfoGetGroupListResp();
		if(json.containsKey("group")) {
			result.setGroup(getGroups(json.getJSONArray("group")));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	/**
	 * 查获取session相关状态和结果
	 *
	 * 可能的status：INQUEUE(队列中), SUCC(成功) 和FAILED(失败)
	 * 当status是SUCC时，返回结果中还包含session对应的结果
	 * 所有session都将在计算完成72小时之后过期，并被自动清除。
	 * status返回值为SUCC仅表示成功取得运行结果，实际任务成功与否请根据result内容判断
	 * @param sessionId
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InfoGetSessionResp getSesson(String sessionId) throws ClientProtocolException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("session_id", sessionId);
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_INFO_GET_SESSION, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		InfoGetSessionResp result = new InfoGetSessionResp();
		if(json.containsKey("session_id")) {
			result.setSession_id(json.getString("session_id"));
			result.setCreate_time(json.getInt("create_time"));
			result.setFinish_time(json.getInt("finish_time"));
			result.setStatus(json.getString("status"));
			result.setResult(json.getJSONObject("result"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	/**
	 * 查询Quota使用情况简报
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InfoGetQuotaResp getQuota() throws ClientProtocolException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_INFO_GET_QUOTA, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		InfoGetQuotaResp result = new InfoGetQuotaResp();
		if(json.containsKey("total")) {
			result.setTotal(json.getInt("total"));
			result.setUsed(json.getInt("used"));
			result.setExceed(json.getInt("exceed"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}

	/**
	 * 获取该App相关的信息
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InfoGetAppResp getApp() throws ClientProtocolException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		
		HttpResponse resp = HttpUtil.doPost(Config.PATH_INFO_GET_APP, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		InfoGetAppResp result = new InfoGetAppResp();
		if(json.containsKey("name")) {
			result.setName(json.getString("name"));
			result.setDescription(json.getString("description"));
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
	
	/**
	 * 解析JSONArray中的FaceInfo
	 * @param faceInfoArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<FaceInfo> getFaceInfo(JSONArray faceInfoArray) {
		JSONObject faceInfoObj = null;
		List<FaceInfo> faceInfos = new ArrayList<FaceInfo>();
		FaceInfo faceInfo = null;
		for(Iterator<JSONObject> i = faceInfoArray.iterator(); i.hasNext(); ) {
			faceInfoObj = JSONObject.fromObject(i.next());
			faceInfo = new FaceInfo();
			faceInfo.setFace_id(faceInfoObj.getString("face_id"));
			faceInfo.setImg_id(faceInfoObj.getString("img_id"));
			faceInfo.setUrl(faceInfoObj.getString("url"));
			faceInfo.setTag(faceInfoObj.getString("tag"));
			faceInfo.setAttribute(getAttribute(faceInfoObj.getJSONObject("attribute")));
			faceInfo.setPosition(getPosition(faceInfoObj.getJSONObject("position")));
			faceInfo.setPerson(getPersons(faceInfoObj.getJSONArray("person")));
			faceInfo.setFaceset(getFacesets(faceInfoObj.getJSONArray("faceset")));
			faceInfos.add(faceInfo);
		}
		return faceInfos;
	}
	
	/**
	 * 解析JSONArray中的Face
	 * @param faceArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Face> getFaces(JSONArray faceArray) {
		JSONObject faceObj = null;
		List<Face> faces = new ArrayList<Face>();
		Face face = null;
		for(Iterator<JSONObject> i = faceArray.iterator(); i.hasNext(); ) {
			faceObj = JSONObject.fromObject(i.next());
			face = new Face();
			face.setFace_id(faceObj.getString("face_id"));
			face.setTag(faceObj.getString("tag"));
			face.setPosition(getPosition(faceObj.getJSONObject("position")));
			faces.add(face);
		}
		return faces;
	}

	/**
	 * 解析JSONArray中的Faceset
	 * @param facesetArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Faceset> getFacesets(JSONArray facesetArray) {
		JSONObject facesetObj = null;
		List<Faceset> facesets = new ArrayList<Faceset>();
		Faceset faceset = null;
		for(Iterator<JSONObject> i = facesetArray.iterator(); i.hasNext(); ) {
			facesetObj = JSONObject.fromObject(i.next());
			faceset = new Faceset();
			faceset.setFaceset_id(facesetObj.getString("faceset_id"));
			faceset.setFaceset_name(facesetObj.getString("faceset_name"));
			faceset.setTag(facesetObj.getString("tag"));
			facesets.add(faceset);
		}
		return facesets;
	}
	
	/**
	 * 解析JSONArray中的Person
	 * @param personArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Person> getPersons(JSONArray personArray) {
		JSONObject personObj = null;
		List<Person> persons = new ArrayList<Person>();
		Person person = null;
		for(Iterator<JSONObject> i = personArray.iterator(); i.hasNext(); ) {
			personObj = JSONObject.fromObject(i.next());
			person = new Person();
			person.setPerson_id(personObj.getString("person_id"));
			person.setPerson_name(personObj.getString("person_name"));
			person.setTag(personObj.getString("tag"));
			persons.add(person);
		}
		return persons;
	}

	/**
	 * 解析JSONArray中的Group
	 * @param groupArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Group> getGroups(JSONArray groupArray) {
		JSONObject groupObj = null;
		List<Group> groups = new ArrayList<Group>();
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

	/**
	 * 解析JSONArray中的Face.Attribute
	 * @param attributeObj
	 * @return
	 */
	private Face.Attribute getAttribute(JSONObject attributeObj) {
		Face.Attribute attribute = new Face.Attribute();
		JSONObject ageObj = attributeObj.getJSONObject("age");
		Face.Age age = new Face.Age();
		age.setValue(ageObj.getInt("value"));
		age.setRange(ageObj.getInt("range"));
		attribute.setAge(age);
		JSONObject genderObj = attributeObj.getJSONObject("gender");
		Face.Gender gender = new Face.Gender();
		gender.setValue(genderObj.getString("value"));
		gender.setConfidence(Float.valueOf(genderObj.getString("confidence")));
		attribute.setGender(gender);
		JSONObject raceObj = attributeObj.getJSONObject("race");
		Face.Race race = new Face.Race();
		race.setValue(raceObj.getString("value"));
		race.setConfidence(Float.valueOf(raceObj.getString("confidence")));
		attribute.setRace(race);
		return attribute;
	}
	
	/**
	 * 解析JSONArray中的Face.Position
	 * @param positionObj
	 * @return
	 */
	private Face.Position getPosition(JSONObject positionObj) {
		Face.Position position = new Face.Position();
		JSONObject centerObj = positionObj.getJSONObject("center");
		PointF center = new PointF(Float.valueOf(centerObj.getString("x")), Float.valueOf(centerObj.getString("y")));
		position.setCenter(center);
		JSONObject eyeLeftObj = positionObj.getJSONObject("eye_left");
		PointF eyeLeft = new PointF(Float.valueOf(eyeLeftObj.getString("x")), Float.valueOf(eyeLeftObj.getString("y")));
		position.setEye_left(eyeLeft);
		JSONObject eyeRightObj = positionObj.getJSONObject("eye_right");
		PointF eyeRight = new PointF(Float.valueOf(eyeRightObj.getString("x")), Float.valueOf(eyeRightObj.getString("y")));
		position.setEye_right(eyeRight);
		JSONObject mouthLeftObj = positionObj.getJSONObject("mouth_left");
		PointF mouthLeft = new PointF(Float.valueOf(mouthLeftObj.getString("x")), Float.valueOf(mouthLeftObj.getString("y")));
		position.setMouth_left(mouthLeft);
		JSONObject mouthRightObj = positionObj.getJSONObject("mouth_right");
		PointF mouthRight = new PointF(Float.valueOf(mouthRightObj.getString("x")), Float.valueOf(mouthRightObj.getString("y")));
		position.setMouth_right(mouthRight);
		position.setWidth(Float.valueOf(positionObj.getString("width")));
		position.setHeight(Float.valueOf(positionObj.getString("height")));
		return position;
	}
}
