package com.action;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Student;
import com.service.IStudentService;

public class RegisterAction extends ActionSupport {
	@Resource(name="dao")
	BaseDao dao;
	@Resource(name="studentService")
	IStudentService studentService;
	
	private Student student;
	private String confirm;
	
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Student getStudent() {
		return student;
	}
	

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if(this.student.getStuNum() == null || this.student.getStuNum().equals("")){
			addFieldError("stuNum", "学号不能为空！");
		}
		if(this.studentService.getStudentByStuNum(this.student.getStuNum()) != null){
			addFieldError("stuNum", "该学号已被注册！");
		}
		if(this.student.getRealName() == "" || this.student.getRealName() == null){
			addFieldError("realName", "真实姓名不能为空！");
		}
		if(this.student.getPassword() == null || this.student.getPassword().equals("")){
			addFieldError("password", "密码不能为空！");
		}
		if(!this.student.getPassword().equals(this.confirm)){
			addFieldError("confirm", "两次密码不一致！");
		}
	}
	/**
	 * 注册用户
	 * @return
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try{
			this.dao.saveOrUpdate(student);
			return "login";
		}catch(Exception e){
			e.printStackTrace();
			return "register";
		}
	}
}
