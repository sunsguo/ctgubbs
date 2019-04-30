package com.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Post;
import com.orm.Reply;
import com.orm.Student;
import com.service.IPostService;
import com.service.IReplyService;

public class ReplyAction extends ActionSupport{
	@Resource(name="replyService")
	IReplyService replyService;
	@Resource(name="postService")
	IPostService postService;
	
	private int pid;
	private List<Reply> replies;
	private Reply reply;
	
	public void setReply(Reply reply){
		this.reply = reply;
	}
	public Reply getReply(){
		return this.reply;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
	//回复帖子
	public String stuReply() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int pid = Integer.parseInt(request.getParameter("pid"));
		Student stu = (Student)ActionContext.getContext().getSession().get("student");
		try{
			Post post = this.postService.loadPost(pid);
			//获取学生帖子
			this.replyService.stuReplyPost(stu, post, reply);
			//涉资海pid，回传给查看帖子的方法
			request.getSession().setAttribute("pid", pid);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return super.ERROR;
		}
	}
	//准备修改回复
	public String prepareModifyReply() throws Exception{ 
		int rid = -1;
		if(ServletActionContext.getRequest().getParameter("rid") != null){
			rid = Integer.parseInt(ServletActionContext.getRequest().getParameter("rid"));
			this.setReply(this.replyService.loadReply(rid));
			return super.SUCCESS;
		}
		return super.ERROR;
	}
	//修改帖子
	public String modifyReply() throws Exception {
		/*
		 * 要先到达修改页面，在提交
		 * 修改内容
		 */
		this.getReply().setContent(this.getReply().getContent());
		if(this.replyService.modifyReply(reply)){
			return "modifySuccess";
		}
		return super.ERROR;
	}
	//删除帖子
	public String deleteReply() throws Exception {
		int rid = -1;
		if(ServletActionContext.getRequest().getParameter("rid") != null){
			rid = Integer.parseInt(ServletActionContext.getRequest().getParameter("rid"));
			if(this.replyService.delReply(rid)){
				return "deleteSuccess";
			}
		}
		return super.ERROR;
	}
}






















