/*
 * 文件名：Group.java
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
 * 功能：Group实体类
 * @author xiaoying
 */
public class Group {
	private String group_id;	//相应group的id
	private String group_name;	//相应group的name
	private String tag;	//group相关的tag
	private List<Person> person;	//属于该group的person信息
	
}
