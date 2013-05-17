/*
 * 文件名：Test.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-14
 * 修改人：xiaoying
 * 修改时间：2013-5-14
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xiaoying.faceplusplus.api.cliet.Client;
import com.xiaoying.faceplusplus.api.entity.request.info.InfoGetFaceReq;
import com.xiaoying.faceplusplus.api.service.FaceService;
import com.xiaoying.faceplusplus.api.service.FacesetService;
import com.xiaoying.faceplusplus.api.service.GroupService;
import com.xiaoying.faceplusplus.api.service.InfoService;
import com.xiaoying.faceplusplus.api.service.PersonService;
import com.xiaoying.faceplusplus.api.service.RecognitionService;
import com.xiaoying.faceplusplus.api.service.TrainService;

/**
 * 测试类
 * @author xiaoying
 */
public class Test {
	public static String APP_KEY = "f7644c4bf304dfb8b0afd1935c9ecf2f";
	public static String APP_SECRET = "pyGS__qcKYwn3yuTvWONluk9ciIfvY8A";

	public static void main(String [] args) {
		
		Client client = new Client(APP_KEY, APP_SECRET);
		try {
			FaceService faceService = new FaceService(client);
			/*DetectFaceReq request = new DetectFaceReq();
//		request.setUrl("http://www.yn.xinhuanet.com/ent/2008-11/11/xin_1331105110914078108772.jp");
			request.setImg(new File("/home/xiaoying/pic13.jpg"));
			System.out.println(faceService.detect(request));*/
			
//			{"face":[{"attribute":{"age":{"range":6,"value":16},"gender":{"confidence":77.9085,"value":"Female"},"race":{"confidence":99.8218,"value":"Asian"}},"face_id":"41f2cb17f81e18541b2635014f232925","position":{"center":{"x":60.023586,"y":32.333332},"eye_left":{"x":49.837265,"y":29.062166},"eye_right":{"x":62.583492,"y":26.953167},"height":19.666668,"mouth_left":{"x":54.26462,"y":38.03583},"mouth_right":{"x":65.07476,"y":37.073833},"nose":{"x":53.909904,"y":33.839},"width":28.066038},"tag":""}],"img_height":707,"img_id":"e589af17363c6010ed3fe36175e47099","img_width":500,"session_id":"48e7415c907446c5b4794fc0304321b1","url":null}


			PersonService person = new PersonService(client);
			
			/*PersonCreateReq req = new PersonCreateReq();
			req.setPerson_name("张娜拉2");
			System.out.println(person.createPerson(req));*/
//			{"added_face":0,"added_group":0,"person_id":"1b200a3a360085752c6231933712eab4","person_name":"张娜拉2","tag":""}
					
			/*PersonDeleteReq req = new PersonDeleteReq();
			req.setPerson_name("73897e8ad43b4aab82b34030d2c08b91");
			Log.e(person.deletePerson(req).toString());*/

			/*PersonAddFaceReq req = new PersonAddFaceReq();
			req.setPerson_id("1b200a3a360085752c6231933712eab4");
			req.setFace_id("2bc15dd0fbaf99e958b46e7ec4b7450d,c7379cc89300baec5afa9eb9266c2123,8192a21d4429e6b13a549fec880e5ffb," +
					"33b4dd89e344306954bee19cb0789a32,b8f0a3e2f161e6d616edf8687a7df991,a0e5510a80a8cdf8624960a438fa045b," +
					"494024de84f7f6999d32494ff5bc0263,5227642c4477d2b4c57764017ed1d91b");
			Log.i(person.addFace(req).toString());*/
			
			/*PersonRemoveFaceReq req = new PersonRemoveFaceReq();
			req.setPerson_id("1b200a3a360085752c6231933712eab4");
			req.setFace_id("2bc15dd0fbaf99e958b46e7ec4b7450d");
			Log.i(person.removeFace(req).toString());*/
			
			/*PersonSetInfoReq req = new PersonSetInfoReq();
			req.setPerson_id("1b200a3a360085752c6231933712eab4");
			req.setName("张娜拉_new");
			req.setTag("这是张娜拉的Person");
			Log.i(person.setPersonInfo(req).toString());*/
			
			/*PersonGetInfoReq req = new PersonGetInfoReq();
			req.setPerson_name("张娜拉_new");
			Log.i(person.getPersonInfo(req).toString());*/
			
			
			GroupService group = new GroupService(client);
			
			/*GroupCreateReq req = new GroupCreateReq();
			req.setGroup_name("测试");
			req.setPerson_id("1b200a3a360085752c6231933712eab4");
			System.out.println(group.createGroup(req));*/
			
			/*GroupDeleteReq req = new GroupDeleteReq();
			req.setGroup_name("My friend2,My friend4");
			Log.i(group.deleteGroup(req).toString());*/
			
			/*GroupAddPersonReq req = new GroupAddPersonReq();
			req.setGroup_id("9ebefaed99e1bc3aaf1669fc3b3763f9");
			req.setPerson_id("3f08cb53e76585c74b5d175b821d2f2f");
			Log.i(group.addPerson(req).toString());*/
			
			/*GroupSetInfoReq req = new GroupSetInfoReq();
			req.setGroup_id("9ebefaed99e1bc3aaf1669fc3b3763f9");
			req.setName("张运迎测试Group");
			req.setTag("This is group by Yunying.Zhang testing");
			Log.i(group.setGroupInfo(req).toString());*/
			
			/*GroupGetInfoReq req = new GroupGetInfoReq();
			req.setGroup_name("张运迎测试Group");
			Log.i(group.getGroupInfo(req).toString());*/
			
			FacesetService faceset = new FacesetService(client);
			
			/*FacesetCreateReq req = new FacesetCreateReq();
			req.setFaceset_name("测试的人脸2");
			req.setFace_id("2bc15dd0fbaf99e958b46e7ec4b7450d,c7379cc89300baec5afa9eb9266c2123,8192a21d4429e6b13a549fec880e5ffb," +
					"33b4dd89e344306954bee19cb0789a32,b8f0a3e2f161e6d616edf8687a7df991,a0e5510a80a8cdf8624960a438fa045b," +
					"494024de84f7f6999d32494ff5bc0263,5227642c4477d2b4c57764017ed1d91b");
			Log.i(faceset.createFaceset(req).toString());*/
//			{"added_face":8,"faceset_id":"8afa298e5c241c31bba3a03dd8762557","faceset_name":"测试的人脸2","tag":""}
			
			/*FacesetDeleteReq req = new FacesetDeleteReq();
			req.setFaceset_name("测试的人脸");
			Log.i(faceset.deleteFaceset(req).toString());*/
			
			/*FacesetAddFaceReq req = new FacesetAddFaceReq();
			req.setFaceset_id("8afa298e5c241c31bba3a03dd8762557");
			req.setFace_id("494024de84f7f6999d32494ff5bc0263,5227642c4477d2b4c57764017ed1d91b,33b4dd89e344306954bee19cb0789a32");
			Log.i(faceset.addFace(req).toString());*/
			
			/*FacesetRemoveFaceReq req = new FacesetRemoveFaceReq();
			req.setFaceset_id("8afa298e5c241c31bba3a03dd8762557");
			req.setFace_id("8192a21d4429e6b13a549fec880e5ffb");
			Log.i(faceset.removeFace(req).toString());*/

			/*FacesetSetInfoReq req = new FacesetSetInfoReq();
			req.setFaceset_name("测试的人脸2");
			req.setName("测试的人脸--by张运迎");
			req.setTag("这是张运迎用来测试的Faceset");
			Log.i(faceset.setFacesetInfo(req).toString());*/
			
			/*FacesetGetInfoReq req = new FacesetGetInfoReq();
			req.setFaceset_id("8afa298e5c241c31bba3a03dd8762557");
			Log.i(faceset.getFacesetInfo(req).toString()); */
			
			
			InfoService info = new InfoService(client);
			
			/*InfoGetImageReq req = new InfoGetImageReq();
			req.setImg_id("934e323a5facd0cce03d5bf3aad65dcd");
			Log.i(info.getImage(req).toString());*/
			
			/*InfoGetFaceReq req = new InfoGetFaceReq();
			req.setFace_id("7ba1ae5cd5fc5892d17539fb975fae5a");
//			req.setFace_id("7ba1ae5cd5fc5892d17539fb975fae5a");
			System.out.println(info.getFace(req));*/
			
//			System.out.println(info.getPersonList());
			
			System.out.println(info.getFacsetList());
			
//			System.out.println(info.getGroupList());
//			System.out.println(info.getSesson("1b4f90bee2a44f0388e92ea32bded885"));
			
//			System.out.println(info.getQuota());
			
//			System.out.println(info.getApp());
			
			TrainService train = new TrainService(client);
			
			/*TrainVerifyReq req = new TrainVerifyReq();
			req.setPerson_id("1b200a3a360085752c6231933712eab4");
			TrainVerifyResp resp = train.trainVerify(req);
			
			if(resp.getError_code() == 0) {
				System.out.println(info.getSesson(resp.getSession_id()));
			}*/
			
			/*TrainSearchReq req = new TrainSearchReq();
			req.setFaceset_name("测试的人脸--by张运迎");
			TrainSearchResp resp = train.trainSearch(req);
			if(resp.getError_code() == 0) {
				System.out.println(info.getSesson(resp.getSession_id()));
			}*/
			
			/*TrainIdentityReq req = new TrainIdentityReq();
//			req.setGroup_id("91d2f283b161edc36bd84c2daab9b38b");
			req.setGroup_name("张运迎测试Group");
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
			req.setPerson_id("1b200a3a360085752c6231933712eab4");
			recognition.verify(req);*/
			
			/*SearchReq req = new SearchReq("5227642c4477d2b4c57764017ed1d91b", "");
			req.setFaceset_name("测试的人脸--by张运迎");
			req.setCount(6);
			System.out.println(recognition.search(req));*/
			
//			IdentityReq req2 = new IdentityReq();
//			req2.setGroup_name("张运迎测试Group");
////			req2.setImg(new File("/home/xiaoying/pic3.jpg"));
//			req2.setKey_face_id("2bc15dd0fbaf99e958b46e7ec4b7450d");
//			System.out.println(recognition.identity(req2));
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
