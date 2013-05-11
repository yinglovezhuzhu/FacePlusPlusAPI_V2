/*
 * 文件名：PersonCreateResp.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-11
 * 修改人：xiaoying
 * 修改时间：2013-5-11
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.response;
/**
 * 功能：创建Person的返回实体类
 * @author xiaoying
 */
public class PersonCreateResp {
	private int added_group;	//成功被加入的group数量
	private int added_face;	//成功加入的face数量
	private String tag;	//person相关的tag
	private String person_name;	//相应person的name
	private String person_id;	//相应person的id
	public int getAdded_group() {
		return added_group;
	}
	public void setAdded_group(int added_group) {
		this.added_group = added_group;
	}
	public int getAdded_face() {
		return added_face;
	}
	public void setAdded_face(int added_face) {
		this.added_face = added_face;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	@Override
	public String toString() {
		return "PersonCreateResp [added_group=" + added_group + ", added_face="
				+ added_face + ", tag=" + tag + ", person_name=" + person_name
				+ ", person_id=" + person_id + "]";
	}
}
