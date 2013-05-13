/*
 * 文件名：FaceService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.service;

import java.io.FileNotFoundException;
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
import com.xiaoying.faceplusplus.api.entity.PointF;
import com.xiaoying.faceplusplus.api.entity.request.face.DetectFaceReq;
import com.xiaoying.faceplusplus.api.entity.response.face.DetectResp;
import com.xiaoying.faceplusplus.api.utils.HttpUtil;
import com.xiaoying.faceplusplus.api.utils.Log;
import com.xiaoying.faceplusplus.api.utils.StringUtil;

/**
 * 功能：
 * @author xiaoying
 */
public class FaceService extends BaseService {
	
	public FaceService(Client client) {
		super(client);
	}

	/**
	 * 人脸检测
	 * @param body 人脸检测请求体
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public DetectResp detect(DetectFaceReq body) throws ClientProtocolException, IOException {
		if(StringUtil.isEmpty(body.getUrl()) && body.getImg() == null) {
			throw new IllegalArgumentException("Must set a image url or image file");
		}
		if(body.getImg() != null && !body.getImg().exists()) {
			throw new FileNotFoundException("File " + body.getImg().getPath() + "not found.");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_key", client.getAppKey());
		params.put("api_secret", client.getAppSecret());
		params.put("img", body.getImg());
		params.put("url", body.getUrl());
		params.put("mode", body.getMode());
		params.put("attribute", body.getAttribute());
		params.put("tag", body.getTag());
		params.put("async", body.isAsync());
		return getResponse(params);
	}
	
	@SuppressWarnings("unchecked")
	private DetectResp getResponse(Map<String, Object> params) throws ClientProtocolException, IOException {
		HttpResponse resp = HttpUtil.doPost(Config.PATH_DETECT, params);
		JSONObject json = JSONObject.fromObject(EntityUtils.toString(resp.getEntity()));
		Log.i(json.toString());
		DetectResp result = new DetectResp();
		if(json.containsKey("session_id")) {
			result.setSession_id(json.getString("session_id"));
			result.setImage_id(json.getString("img_id"));
			result.setUrl(json.getString("url"));
			result.setImg_width(json.getInt("img_width"));
			result.setImg_height(json.getInt("img_height"));
			JSONArray faceArray = json.getJSONArray("face");
			JSONObject faceObj = null;
			List<Face> faces = new ArrayList<Face>();
			Face face = null;
			for(Iterator<JSONObject> i = faceArray.iterator(); i.hasNext(); ) {
				faceObj = JSONObject.fromObject(i.next());
				face = new Face();
				face.setFace_id(faceObj.getString("face_id"));
				face.setTag(faceObj.getString("tag"));
				face.setAttribute(getAttribute(faceObj.getJSONObject("attribute")));
				face.setPosition(getPosition(faceObj.getJSONObject("position")));
				faces.add(face);
			}
			result.setFace(faces);
		} else {
			result.setError(json.getString("error"));
			result.setError_code(json.getInt("error_code"));
		}
		return result;
	}
	
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
