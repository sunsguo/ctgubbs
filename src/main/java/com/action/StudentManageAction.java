package com.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Student;
import com.service.IStudentService;
import com.tools.SendMail;

public class StudentManageAction extends ActionSupport {
	@Resource(name="studentService")
	IStudentService studentService;
	
	private Student student;
	private String psw;
	private String newPsw;
	private String confirmPsw;
	public void setStudent(Student student){
		this.student = student;
	}
	public Student getStudent(){
		return this.student;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getNewPsw() {
		return newPsw;
	}
	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}
	public String getConfirmPsw() {
		return confirmPsw;
	}
	public void setConfirmPsw(String confrimPsw) {
		this.confirmPsw = confrimPsw;
	}
	
	//显示个人信息
	public String personalStuInfo() throws Exception{
		Student student = (Student)ActionContext.getContext().getSession().get("student");
		if(student != null){
			this.setStudent(this.studentService.getStudentByStuNum(student.getStuNum()));
			return super.SUCCESS;
		}
		return super.ERROR;
	}
	//修改个人信息
	public String modifyStuInfo() throws Exception{
		Student student = (Student)ActionContext.getContext().getSession().get("student");
		Student stu = this.studentService.getStudentByStuNum(student.getStuNum());
		stu.setNickName(this.student.getNickName());
		stu.setQq(this.student.getQq());
		stu.setEmail(this.student.getEmail());
		if(this.studentService.modifyStudent(stu)){
			return "modifySuccess";
		}
		return super.ERROR;
	}
	//修改密码
	public String modifyPsw() throws Exception{
		//获得学生登陆信息
		Student student = (Student)ActionContext.getContext().getSession().get("student");
		Student stu = this.studentService.getStudentByStuNum(student.getStuNum());
		if(this.psw.equals(stu.getPassword()) && this.newPsw.equals(this.confirmPsw)){
			stu.setPassword(newPsw);
			this.studentService.modifyStudent(stu);
			addActionMessage("修改密码成功！");
			return "modifyPswSuccess";
		}
		return super.ERROR;
	}

	//进入修改密码页面
	public String goToModigyPsw() throws Exception{
		return "goToModigyPsw";
	}

	//进入找回密码页面
	public String findPwd() throws Exception{
		return "findPwd";
	}


	//找回密码
	public String sendPwd() throws Exception{
		Student stu = this.studentService.getStudentByStuNum(student.getStuNum());
		if(stu == null){
			addFieldError("student.stuNum", "账号错误或者没有注册");
		}else {
			try{
				new SendMail().sendMail(stu.getEmail(), stu.getPassword());
				return "findSuccess";
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return this.findPwd();
	}
}




















