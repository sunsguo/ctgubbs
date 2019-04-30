package com.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Admin;
import com.service.IAdminService;

public class AdminManageAction extends ActionSupport {
	@Resource(name="adminService")
	IAdminService adminService;
	
	private Admin admin;
	
	public void setAdmin(Admin admin){
		this.admin = admin;
	}
	public Admin getAdmin(){
		return this.admin;
	}
	@Override
	public String execute() throws Exception{
		return super.execute();
	}
	//显示个人信息
	public String personalAdminInfo() throws Exception{
		Admin sessionAdmin = (Admin)ActionContext.getContext().getSession().get("admin");
		if(sessionAdmin != null){
			this.setAdmin(this.adminService.loadAdmin(sessionAdmin.getId()));
			return super.SUCCESS;
		}
		return super.ERROR;
	}
	//修改个人信息
	public String modifyAdminInfo(){
		Admin sessionAdmin = (Admin)ActionContext.getContext().getSession().get("admin");
		sessionAdmin.setNickName(admin.getNickName());
		sessionAdmin.setQq(admin.getQq());
		sessionAdmin.setEmail(admin.getEmail());
		if(this.adminService.updateAdmin(sessionAdmin)){
			return "success";
		}
		return super.ERROR;
	}
}
