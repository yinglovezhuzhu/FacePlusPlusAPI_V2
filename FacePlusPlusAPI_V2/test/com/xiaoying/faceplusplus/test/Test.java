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
import com.xiaoying.faceplusplus.api.entity.request.info.InfoGetFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.info.InfoGetImageReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonAddFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonCreateReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonDeleteReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonGetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonRemoveFaceReq;
import com.xiaoying.faceplusplus.api.entity.request.person.PersonSetInfoReq;
import com.xiaoying.faceplusplus.api.entity.request.recognition.CompareReq;
import com.xiaoying.faceplusplus.api.entity.request.recognition.IdentityReq;
import com.xiaoying.faceplusplus.api.entity.request.recognition.SearchReq;
import com.xiaoying.faceplusplus.api.entity.request.recognition.VerifyReq;
import com.xiaoying.faceplusplus.api.entity.request.train.TrainIdentityReq;
import com.xiaoying.faceplusplus.api.entity.request.train.TrainSearchReq;
import com.xiaoying.faceplusplus.api.entity.request.train.TrainVerifyReq;
import com.xiaoying.faceplusplus.api.entity.response.group.GroupGetInfoResp;
import com.xiaoying.faceplusplus.api.entity.response.person.PersonCreateResp;
import com.xiaoying.faceplusplus.api.entity.response.train.TrainIdentityResp;
import com.xiaoying.faceplusplus.api.entity.response.train.TrainSearchResp;
import com.xiaoying.faceplusplus.api.entity.response.train.TrainVerifyResp;
import com.xiaoying.faceplusplus.api.service.FaceService;
import com.xiaoying.faceplusplus.api.service.FacesetService;
import com.xiaoying.faceplusplus.api.service.GroupService;
import com.xiaoying.faceplusplus.api.service.GroupingService;
import com.xiaoying.faceplusplus.api.service.InfoService;
import com.xiaoying.faceplusplus.api.service.PersonService;
import com.xiaoying.faceplusplus.api.service.RecognitionService;
import com.xiaoying.faceplusplus.api.service.TrainService;
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
			/*FaceService faceService = new FaceService(client);
			DetectFaceReq request = new DetectFaceReq();
//		request.setUrl("http://www.yn.xinhuanet.com/ent/2008-11/11/xin_1331105110914078108772.jp");
			request.setImg(new File("/home/xiaoying/pic18.jpg"));
			System.out.println(faceService.detect(request));*/
			
			PersonService person = new PersonService(client);
//			{"face":[{"attribute":{"age":{"range":5,"value":13},"gender":{"confidence":58.5597,"value":"Female"},"race":{"confidence":99.9887,"value":"Asian"}},"face_id":"7ba1ae5cd5fc5892d17539fb975fae5a","position":{"center":{"x":56.416668,"y":37.333332},"eye_left":{"x":46.695,"y":27.863333},"eye_right":{"x":63.146168,"y":24.496445},"height":40,"mouth_left":{"x":48.730167,"y":46.57689},"mouth_right":{"x":56.72767,"y":47.309334},"nose":{"x":53.629665,"y":41.008},"width":30.166668},"tag":""},{"attribute":{"age":{"range":5,"value":19},"gender":{"confidence":56.4714,"value":"Female"},"race":{"confidence":87.2965,"value":"Asian"}},"face_id":"f196397c87c0957701c4468499e14fcc","position":{"center":{"x":26.25,"y":51.11111},"eye_left":{"x":22.220667,"y":40.927334},"eye_right":{"x":35.418,"y":45.04889},"height":37.77778,"mouth_left":{"x":21.5865,"y":61.627556},"mouth_right":{"x":32.960835,"y":61.746887},"nose":{"x":30.647667,"y":54.41111},"width":28.5},"tag":""}],"img_height":745,"img_id":"934e323a5facd0cce03d5bf3aad65dcd","img_width":993,"session_id":"3e6c761c006548f293fc3a26b85ee75f","url":null}

			/*PersonCreateReq req = new PersonCreateReq();
			req.setPerson_name("张娜拉");
			System.out.println(person.createPerson(req));*/
//			{"added_face":0,"added_group":0,"person_id":"fed5dddcbdc63d9c2358d8d1a4b16dd6","person_name":"Love","tag":""}
//			{"added_face":0,"added_group":0,"person_id":"c293de26a2eb22725ebb2b2925006164","person_name":"Love2","tag":""}
//			{"added_face":0,"added_group":0,"person_id":"b1d56121e23b85078e9a203a420464f5","person_name":"Love3","tag":""}
//			{"added_face":0,"added_group":0,"person_id":"be2a25b208a5a73f905507acca134cb3","person_name":"Love4","tag":""}
//			{"added_face":0,"added_group":0,"person_id":"3f08cb53e76585c74b5d175b821d2f2f","person_name":"张娜拉","tag":""}
					
			/*PersonDeleteReq req = new PersonDeleteReq();
			req.setPerson_name("Love,Love2");
			Log.e(person.deletePerson(req).toString());*/

			/*PersonAddFaceReq req = new PersonAddFaceReq();
			req.setPerson_id("3f08cb53e76585c74b5d175b821d2f2f");
			req.setFace_id("2bc15dd0fbaf99e958b46e7ec4b7450d,c7379cc89300baec5afa9eb9266c2123,8192a21d4429e6b13a549fec880e5ffb," +
					"33b4dd89e344306954bee19cb0789a32,b8f0a3e2f161e6d616edf8687a7df991,a0e5510a80a8cdf8624960a438fa045b," +
					"494024de84f7f6999d32494ff5bc0263,5227642c4477d2b4c57764017ed1d91b");
			person.addFace(req);/*
			
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
			req.setGroup_name("Face++Test");
			req.setPerson_id("3f08cb53e76585c74b5d175b821d2f2f");
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
			req.setFace_id("2bc15dd0fbaf99e958b46e7ec4b7450d,c7379cc89300baec5afa9eb9266c2123,8192a21d4429e6b13a549fec880e5ffb," +
					"33b4dd89e344306954bee19cb0789a32,b8f0a3e2f161e6d616edf8687a7df991,a0e5510a80a8cdf8624960a438fa045b," +
					"494024de84f7f6999d32494ff5bc0263,5227642c4477d2b4c57764017ed1d91b");
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
			
//			GroupingService grouping = new GroupingService(client);
//			GroupingFaceReq req = new GroupingFaceReq();
//			req.setFaceset_id("d83d0a5c9789ff3919084030d741998f");
//			grouping.groupingFace(req);
			
			
			InfoService info = new InfoService(client);
			
			/*InfoGetImageReq req = new InfoGetImageReq();
			req.setImg_id("934e323a5facd0cce03d5bf3aad65dcd");
			System.out.println(info.getImage(req));*/
			
			/*InfoGetFaceReq req = new InfoGetFaceReq();
			req.setFace_id("7ba1ae5cd5fc5892d17539fb975fae5a,8192a21d4429e6b13a549fec880e5ffb");
//			req.setFace_id("7ba1ae5cd5fc5892d17539fb975fae5a");
			System.out.println(info.getFace(req));*/
			
//			System.out.println(info.getPersonList());
			
//			System.out.println(info.getFacsetList());
			
//			System.out.println(info.getGroupList());
//			System.out.println(info.getSesson("1b4f90bee2a44f0388e92ea32bded885"));
			
//			System.out.println(info.getQuota());
			
//			System.out.println(info.getApp());
			
			TrainService train = new TrainService(client);
			
			/*TrainVerifyReq req = new TrainVerifyReq();
			req.setPerson_id("3f08cb53e76585c74b5d175b821d2f2f");
			TrainVerifyResp resp = train.trainVerify(req);
			
			if(resp.getError_code() == 0) {
				System.out.println(info.getSesson(resp.getSession_id()));
			}*/
			
			/*TrainSearchReq req = new TrainSearchReq();
			req.setFaceset_name("测试的人脸");
			TrainSearchResp resp = train.trainSearch(req);
			if(resp.getError_code() == 0) {
				System.out.println(info.getSesson(resp.getSession_id()));
			}*/
			
			/*TrainIdentityReq req = new TrainIdentityReq();
//			req.setGroup_id("91d2f283b161edc36bd84c2daab9b38b");
			req.setGroup_name("Face++Test");
			TrainIdentityResp resp = train.trainIdentity(req);
			if(resp.getError_code() == 0) {
				System.out.println(info.getSesson(resp.getSession_id()));
			}*/
			
			RecognitionService recognition = new RecognitionService(client);
			/*CompareReq req = new CompareReq("2bc15dd0fbaf99e958b46e7ec4b7450d", "c7379cc89300baec5afa9eb9266c2123");
			req.setAsync(true);
			System.out.println(recognition.compare(req));*/
			
			/*VerifyReq req = new VerifyReq();
			req.setFace_id("5227642c4477d2b4c57764017ed1d91b");
			req.setPerson_id("3f08cb53e76585c74b5d175b821d2f2f");
			recognition.verify(req);*/
			
			/*SearchReq req = new SearchReq("5227642c4477d2b4c57764017ed1d91b", "");
			req.setFaceset_name("测试的人脸");
			req.setCount(6);
			System.out.println(recognition.search(req));*/
			
			IdentityReq req2 = new IdentityReq();
			req2.setGroup_name("Face++Test");
//			req2.setImg(new File("/home/xiaoying/pic3.jpg"));
			req2.setKey_face_id("2bc15dd0fbaf99e958b46e7ec4b7450d");
			System.out.println(recognition.identity(req2));
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
