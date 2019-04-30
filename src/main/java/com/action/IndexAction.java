package com.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Admin;
import com.orm.Board;
import com.orm.Post;
import com.orm.Student;
import com.service.IBoardService;
import com.service.IPostService;
import com.service.IStudentService;

public class IndexAction extends ActionSupport{
	@Resource(name="studentService")
	IStudentService studentService;
	@Resource(name="boardService")
	IBoardService boardService;
	@Resource(name="postService")
	IPostService postService;
	
	private List<Board> rootBoard;
	private int todayNum;
	private int yesterdayNum;
	private int highestNum;
	private int total;
	private Student student;
	private List<Post> hotPosts;
	
	//显示板块
	public List<Board> getRootBoard() {
		return rootBoard;
	}
	
	//火热帖子排行
	public void setRootBoard(List<Board> rootBoard) {
		this.rootBoard = rootBoard;
	}
	public int getTodayNum() {
		return todayNum;
	}
	public void setTodayNum(int todayNum) {
		this.todayNum = todayNum;
	}
	public int getYesterdayNum() {
		return yesterdayNum;
	}
	public void setYesterdayNum(int yesterdayNum) {
		this.yesterdayNum = yesterdayNum;
	}
	public int getHighestNum() {
		return highestNum;
	}
	public void setHighestNum(int highestNum) {
		this.highestNum = highestNum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<Post> getHotPosts() {
		return hotPosts;
	}
	public void setHotPosts(List<Post> hotPosts) {
		this.hotPosts = hotPosts;
	}
	//初始化bbs主页面
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//Student为Po对象S
		try{
			Student sessionStudent = (Student)ActionContext.getContext().getSession().get("student");
			Admin sessionAdmin = (Admin)ActionContext.getContext().getSession().get("admin");
			if(sessionStudent != null){
				this.setStudent(this.studentService.getStudentByStuNum(sessionStudent.getStuNum()));
			}
			
			//加载板块
			this.setRootBoard(this.boardService.loadRootBoards());
			this.setHotPosts(this.postService.rankPosts(10));
			this.setTotal(this.postService.countTodayPost());
			this.setYesterdayNum(this.postService.countYesterdayPost());
			this.setTodayNum(this.postService.countTodayPost());
			return super.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}
}























