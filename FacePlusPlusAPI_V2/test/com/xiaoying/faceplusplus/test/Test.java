/*
 * 鏂囦欢鍚嶏細Test.java
 * 鐗堟潈锛�鐗堟潈>
 * 鎻忚堪锛�鎻忚堪>
 * 鍒涘缓浜猴細xiaoying
 * 鍒涘缓鏃堕棿锛�013-5-10
 * 淇敼浜猴細xiaoying
 * 淇敼鏃堕棿锛�013-5-10
 * 鐗堟湰锛歷1.0
 */
package com.xiaoying.faceplusplus.test;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xiaoying.faceplusplus.api.cliet.Client;
import com.xiaoying.faceplusplus.api.entity.request.face.DetectFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetAddFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetDeleteReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetGetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetRemoveFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.faceset.FacesetSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupAddPersonReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupDeleteReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupGetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.group.GroupSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.grouping.GroupingFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonAddFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonDeleteReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonGetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonRemoveFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupGetInfoResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonCreateResp;
import com.xiaoying.faceplusplus.api.service.FaceService;
import com.xiaoying.faceplusplus.api.service.FacesetService;
import com.xiaoying.faceplusplus.api.service.GroupService;
import com.xiaoying.faceplusplus.api.service.GroupingService;
import com.xiaoying.faceplusplus.api.service.PersonService;
import com.xiaoying.faceplusplus.api.utils.Log;

/**
 * 鍔熻兘锛氭祴璇曠被
 * @author xiaoying
 */
public class Test {
	public static String APP_KEY = "f7644c4bf304dfb8b0afd1935c9ecf2f";
	public static String APP_SECRET = "pyGS__qcKYwn3yuTvWONluk9ciIfvY8A";

	public static void main(String [] args) {
		
		Client client = new Client(APP_KEY, APP_SECRET);
		try {
			/*FaceService faceService = new FaceService(APP_KEY, APP_SECRET);
			DetectFaceReq request = new DetectFaceReq();
		request.setUrl("http://www.yn.xinhuanet.com/ent/2008-11/11/xin_1331105110914078108772.jp");
//			request.setImg(new File("/home/xiaoying/pic3.jpg"));
			System.out.println(faceService.detect(request));*/
			
			PersonService person = new PersonService(client);
			
			/*PersonCreateReq req = new PersonCreateReq();
			req.setPerson_name("Love3");
			System.out.println(person.createPerson(req));
			req.setPerson_name("Love4");
			System.out.println(person.createPerson(req));*/
//			{"added_face":0,"added_group":0,"person_id":"fed5dddcbdc63d9c2358d8d1a4b16dd6","person_name":"Love","tag":""}
//			{"added_face":0,"added_group":0,"person_id":"c293de26a2eb22725ebb2b2925006164","person_name":"Love2","tag":""}
//			{"added_face":0,"added_group":0,"person_id":"b1d56121e23b85078e9a203a420464f5","person_name":"Love3","tag":""}
//			{"added_face":0,"added_group":0,"person_id":"be2a25b208a5a73f905507acca134cb3","person_name":"Love4","tag":""}
					
			/*PersonDeleteReq req = new PersonDeleteReq();
			req.setPerson_name("Love,Love2");
			Log.e(person.deletePerson(req).toString());*/

			/*PersonAddFaceReq req = new PersonAddFaceReq();
			req.setPerson_id("be2a25b208a5a73f905507acca134cb3");
			req.setFace_id("c829e7ba8188e902e3ee8ecceca5f3f5,383385a62ded03daf49a51268d830bcf,efcc76fa92a68742e8f60160bc5abcfb,684d6e2023ebc4417c47d410fbdc68ba,f139ab40225a8d5cb024299ae76d1269");
			person.addFace(req);*/
			
			/*PersonRemoveFaceReq req = new PersonRemoveFaceReq();
			req.setPerson_id("fed5dddcbdc63d9c2358d8d1a4b16dd6");
			req.setFace_id("c829e7ba8188e902e3ee8ecceca5f3f5,383385a62ded03daf49a51268d830bcf");
			person.removeFace(req);*/
			
			/*PersonSetInfoReq req = new PersonSetInfoReq();
			req.setPerson_id("fed5dddcbdc63d9c2358d8d1a4b16dd6");
			req.setName("New Love");
			req.setTag("This is my lovely");
			person.setPersonInfo(req);*/
			
			/*PersonGetInfoReq req = new PersonGetInfoReq();
			req.setPerson_name("张娜拉");
			person.getPersonInfo(req);*/
			
			
			GroupService group = new GroupService(client);
			
			/*GroupCreateReq req = new GroupCreateReq();
			req.setGroup_name("朋友");
			req.setPerson_id("fed5dddcbdc63d9c2358d8d1a4b16dd6");
			System.out.println(group.createGroup(req));*/
			
			/*GroupDeleteReq req = new GroupDeleteReq();
			req.setGroup_name("My friend4,My friend5");
			group.deleteGroup(req);*/
			
			/*GroupAddPersonReq req = new GroupAddPersonReq();
			req.setGroup_name("My friend3");
			req.setPerson_id("b1d56121e23b85078e9a203a420464f5,be2a25b208a5a73f905507acca134cb3");
			group.addPerson(req);*/
			
			/*GroupSetInfoReq req = new GroupSetInfoReq();
			req.setGroup_name("My friend3");
			req.setName("My Best Friend");
			req.setTag("This is all my Best frend");
			group.setGroupInfo(req);*/
			
//			GroupGetInfoReq req = new GroupGetInfoReq();
//			req.setGroup_name("My Best Friend");
//			group.getGroupInfo(req);
			
			FacesetService faceset = new FacesetService(client);
			
			/*FacesetCreateReq req = new FacesetCreateReq();
			req.setFaceset_name("测试的人脸");
			req.setFace_id("2bc15dd0fbaf99e958b46e7ec4b7450d");
			faceset.createFaceset(req);*/
			
			/*FacesetDeleteReq req = new FacesetDeleteReq();
			req.setFaceset_id("2bc15dd0fbaf99e958b46e7ec4b7450d");
			faceset.deleteFaceset(req);*/
			
			/*FacesetAddFaceReq req = new FacesetAddFaceReq();
			req.setFaceset_id("d83d0a5c9789ff3919084030d741998f");
			req.setFace_id("8192a21d4429e6b13a549fec880e5ffb,33b4dd89e344306954bee19cb0789a32");
			faceset.addFace(req);*/
			
			/*FacesetRemoveFaceReq req = new FacesetRemoveFaceReq();
			req.setFaceset_id("d83d0a5c9789ff3919084030d741998f");
			req.setFace_id("8192a21d4429e6b13a549fec880e5ffb,33b4dd89e344306954bee19cb0789a32");
			faceset.removeFace(req);*/

			/*FacesetSetInfoReq req = new FacesetSetInfoReq();
			req.setFaceset_name("测试的人脸");
			req.setName("测试的人脸--测试");
			req.setTag("这是用来测试的Faceset");
			faceset.setFacesetInfo(req);*/
//			{"faceset_id":"d83d0a5c9789ff3919084030d741998f","faceset_name":"测试的人脸--测试","tag":"这是用来测试的Faceset"}
			
			/*FacesetGetInfoReq req = new FacesetGetInfoReq();
			req.setFaceset_id("d83d0a5c9789ff3919084030d741998f");
			faceset.getFacesetInfo(req);*/
			
			GroupingService grouping = new GroupingService(client);
			GroupingFaceReq req = new GroupingFaceReq();
			req.setFaceset_id("d83d0a5c9789ff3919084030d741998f");
			grouping.groupingFace(req);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
