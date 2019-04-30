package com.service;

import com.orm.Admin;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public interface IAdminService {
	/*
	 * 获取管理员
	 */
	public Admin loadAdmin(int id);
	
	/**
	 * 添加管理员
	 */
	public void addAdmin(Admin admin) ;
	/**
	 * 修改管理员信息
	 */
	public boolean updateAdmin(Admin admin);
	
}
