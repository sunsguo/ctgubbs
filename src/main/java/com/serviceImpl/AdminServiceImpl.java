package com.serviceImpl;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.orm.Admin;
import com.service.IAdminService;

public class AdminServiceImpl implements IAdminService {
	@Resource(name="dao")
	BaseDao dao;

	@Override
	public Admin loadAdmin(int id) {
		// TODO Auto-generated method stub
		return (Admin)dao.loadById(Admin.class, id);
	}
	
	@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		this.dao.saveOrUpdate(admin);
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		try{
			this.dao.saveOrUpdate(admin);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
