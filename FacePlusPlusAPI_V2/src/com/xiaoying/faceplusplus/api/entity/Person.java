/*
 * 文件名：Person.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity;

import java.util.List;

/**
 * 功能：人实体类
 * @author xiaoying
 */
public class Person {
	private String person_id;//	string	相应person的id
	private String person_name;//	string	相应person的name
	private String tag;//	string	person相关的tag
	private List<Face> face; //Person下的所有人脸
}
