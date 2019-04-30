package com.service;

import java.util.List;

import com.orm.Post;
import com.orm.Reply;
import com.orm.Student;

public interface IReplyService {
	/*
	 * 加载一条回复
	 */
	public Reply loadReply(int rId);
	/*
	 * 获取回复
	 */
	public List<Reply> getReplysByPid(int pId);
	/*
	 * 学生回帖
	 */
	public boolean stuReplyPost(Student stu, Post post, Reply reply);
	/*
	 * 修改回复
	 */
	public boolean modifyReply(Reply reply);
	/*
	 * 删除回复
	 */
	public boolean delReply(int rId);
}
