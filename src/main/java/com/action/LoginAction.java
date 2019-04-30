package com.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Admin;
import com.orm.Board;
import com.orm.Post;
import com.orm.Student;
import com.service.IBoardService;
import com.service.ILoginService;
import com.service.IPostService;
import com.service.IStudentService;

public class LoginAction extends ActionSupport{
	private Student student;
	private Admin admin;
	private String tip;
	private int pageNo;	//当前页
	private int pageSize;	//每页条数
	private int count;
	private List<Post> list;
	private int bid;
	private Board board;
	
	private String confirm;
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	@Resource(name="loginService")
	ILoginService loginService;
	@Resource(name="studentService")
	IStudentService studentService;
	@Resource(name="postService")
	IPostService postService;
	@Resource(name="boardService")
	IBoardService boardService;
	
	public void setStudent(Student student) {
		this.student = student;
	}
	public Student getStudent() {
		return student;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Post> getList() {
		return list;
	}
	public void setList(List<Post> list) {
		this.list = list;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}

	public String stuLogin() throws Exception {
		// TODO Auto-generated method stub
		//登陆前前清除所有的Session		
		ActionContext.getContext().getSession().clear();
		Student stu = this.loginService.stuLogin(student);
		if(stu != null){
			ActionContext.getContext().getSession().put("student", stu);
			List<Post> list = this.postService.allPost();
			this.setList(list);
			System.out.println("ok");
			return "loginSuccess";
		}
		addActionMessage("用户名或密码错误");
		System.out.println("bad");
		return super.INPUT;
	}
	public String adminLogin()throws Exception{
		ActionContext.getContext().getSession().clear();
		Admin a = this.loginService.adminLogin(admin);
		if(a != null){
			ActionContext.getContext().getSession().put("admin", a);
			List<Post> list = this.postService.allPost();
			this.setList(list);
			System.out.println("ok");
			return "loginSuccess";
		}
		addActionMessage("用户名或密码错误");
		System.out.println("bad");
		return super.INPUT;
	}
	//帖子分页列表
	public String showAll(){
		this.setPageSize(15);
		HttpServletRequest request = ServletActionContext.getRequest();
		Student tempStudent = (Student)request.getSession().getAttribute("student");
		this.setBoard(this.boardService.loadBoard(this.getBid()));
		if(request.getParameter("page") != null){
			this.setPageNo(Integer.parseInt(request.getParameter("page")));
			System.out.println(this.pageNo);
		}else{
			this.setPageNo(1);
		}
		if(request.getParameter("bid") != null){
			this.setBid(Integer.parseInt(request.getParameter("bid")));
		}
		try{
			this.setCount(this.postService.getPostsCount());
			this.setList(this.postService.pageAllPost(bid, pageNo, pageSize));
			if(tempStudent != null){
				this.setStudent(this.studentService.getStudentByStuNum(tempStudent.getStuNum()));
			}
			return super.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}
	
	//注销登陆
	public String exit(){
		ActionContext.getContext().getSession().clear();
		return "exit";
	}
}

















