package com.action;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Admin;
import com.orm.Board;
import com.orm.Post;
import com.orm.Reply;
import com.orm.Student;
import com.service.IAdminService;
import com.service.IBoardService;
import com.service.IPostService;
import com.service.IStudentService;

public class PostAction extends ActionSupport{
	@Resource(name="postService")
	IPostService postService;
	@Resource(name="studentService")
	IStudentService studentService;
	@Resource(name="adminService")
	IAdminService adminService;
	@Resource(name="boardService")
	IBoardService boardService;
	
	private Post post;
	private List<Post> list;
	private Set<Reply> replies;
	private List<Post> myPosts;
	private Student student;
	private Admin admin;
	private String result;
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<Post> getList() {
		return list;
	}
	public void setList(List<Post> list) {
		this.list = list;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public List<Post> getMyPosts() {
		return myPosts;
	}
	public void setMyPosts(List<Post> myPosts) {
		this.myPosts = myPosts;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	//单击帖子管理默认执行改方法
	public String post() throws Exception {
		// TODO Auto-generated method stub
		this.setList(this.postService.allPost());
		return super.SUCCESS;
	}
	//跳转到发帖页面
	public String preparePost() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		Student s = (Student)request.getSession().getAttribute("student");
		if(s != null){
			this.setStudent(studentService.getStudentByStuNum(s.getStuNum()));
		}
		return "preparePost";
	}
	//发帖
	public String addPost() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		int bid = 0;
		if(request.getParameter("bid") != null){
			bid = Integer.parseInt(request.getParameter("bid"));
		}
		Post p = this.getPost();
		Board b = this.boardService.loadBoard(bid);
		Student s = (Student)request.getSession().getAttribute("student");
		Admin a = (Admin)request.getSession().getAttribute("admin");
		if(s != null){
			p.setStudent(s);
			p.setBoard(b);
			p.setContent(getPost().getContent());
			p.setPublishTime(new Timestamp(new java.util.Date().getTime()));
			p.setCount(0);
			if(this.postService.saveOrUpdate(this.getPost())){
				post = p;
				return "postSuccess";
			}
			return super.ERROR;
		}
		if(a != null){
			p.setStudent(s);
			p.setBoard(b);
			p.setContent(getPost().getContent());
			p.setPublishTime(new Timestamp(new java.util.Date().getTime()));
			p.setCount(0);
			if(this.postService.saveOrUpdate(this.getPost())){
				post = p;
				return "postSuccess";
			}
			return super.ERROR;
		}
		return super.ERROR;
	}
	//查看帖子
	@SuppressWarnings("unchecked")
	public String viewDetail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//学生登陆
		if(request.getSession().getAttribute("student") != null){
			Student s = (Student)request.getSession().getAttribute("student");
			this.setStudent(this.studentService.getStudentByStuNum(s.getStuNum()));
		}
		//管理员登陆
		if(request.getSession().getAttribute("admin") != null){
			Admin a = (Admin)request.getSession().getAttribute("admin");
			this.setAdmin(a);
		}
		int pid = Integer.parseInt(request.getParameter("pid"));
		Post p = this.postService.loadPost(pid);
		//令点击量增加一
		p.setCount(p.getCount() == null ? 0 : p.getCount() + 1);
		this.postService.saveOrUpdate(p);
		this.setPost(this.postService.loadPost(pid));
		if(getPost() != null){
			setReplies(this.postService.loadPost(pid).getReplies());
			return "viewDetail";
		}
		return super.ERROR;
	}
	//分页
	public void viewAll(int pageNo,int pageSize){
		int bid = Integer.parseInt(ServletActionContext.getRequest().getParameter("bid"));
		setList(this.postService.pageAllPost(bid, pageNo, pageSize));
	}
	//查看我的帖子
	public String viewPostByUser(){
		Student s = (Student)ActionContext.getContext().getSession().get("student");
		Admin a = (Admin)ActionContext.getContext().getSession().get("admin");
		if(s != null){
			this.setMyPosts(this.postService.allPostsByUser(s));
			this.setStudent(this.studentService.getStudentByStuNum(s.getStuNum()));
			return "myposts";
		}
		if(a != null){
			this.setMyPosts(this.postService.allPostsByUser(a));
			this.setAdmin(this.adminService.loadAdmin(a.getId()));
			return "myposts";
		}
		return super.ERROR;
	}
	//准备修改
	public String prepareModify() throws Exception {
		int pid = -1;
		if(ServletActionContext.getRequest().getParameter("pid") != null){
			pid = Integer.parseInt(ServletActionContext.getRequest().getParameter("pid"));
		}
		this.setPost(this.postService.loadPost((pid)));
		return "prepareSuccess";
	}
	//修改帖子
	public String modifyPost() throws Exception {
		int pid = -1;
		if(ServletActionContext.getRequest().getParameter("pid") != null){
			pid = Integer.parseInt(ServletActionContext.getRequest().getParameter("pid"));
			Post tempPost = this.postService.loadPost(pid);
			tempPost.setName(this.getPost().getName());
			tempPost.setContent(this.getPost().getContent());
			if(this.postService.saveOrUpdate(tempPost)){
				this.setResult("Mok");
				return "modifySuccess";
			}
			return super.ERROR;
		}
		return super.ERROR;
	}
	//删除帖子
	public String deletePost() throws Exception {
		int pid = -1;
		if(ServletActionContext.getRequest().getParameter("pid") != null){
			pid = Integer.parseInt(ServletActionContext.getRequest().getParameter("pid"));
			if(this.postService.deletePost(pid)){
				this.setResult("Dok");
				return "deleteSuccess";
			}
			return super.ERROR;
		}
		return super.ERROR;
	}
	//按帖子名称搜帖子
	public String searchPost() throws Exception {
		//先要输入关键字，才能执行搜索函数
		try{
			String key = this.getPost().getName().trim();	//去除空格
			if(key != null){
				this.setList(this.postService.searchPosts(key));
				return "searchSuccess";
			}
			return super.ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}
}


























