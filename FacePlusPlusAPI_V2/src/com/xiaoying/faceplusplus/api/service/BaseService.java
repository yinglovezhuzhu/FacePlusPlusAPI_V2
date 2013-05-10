/*
 * 文件名：BaseService.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.service;

import com.xiaoying.faceplusplus.api.client.Client;

/**
 * 功能：
 * @author xiaoying
 */
public class BaseService {
	protected Client client;
	
	public BaseService(Client client) {
		this.client = client;
	}
	
	protected void setClient(Client client) {
		this.client = client;
	}
	
	protected Client getClient() {
		return this.client;
	}
}
