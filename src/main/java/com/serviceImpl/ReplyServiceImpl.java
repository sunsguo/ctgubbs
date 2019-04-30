package com.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.orm.Post;
import com.orm.Reply;
import com.orm.Student;
import com.service.IReplyService;

public class ReplyServiceImpl implements IReplyService {
	@Resource(name="dao")
	BaseDao dao;
	
	@Override
	public Reply loadReply(int rId) {
		// TODO Auto-generated method stub
		return (Reply)dao.loadById(Reply.class, new Integer(rId));
	}

	@Override
	public List<Reply> getReplysByPid(int pId) {
		// TODO Auto-generated method stub
		try{
			List<Reply> list = dao.query("from Reply as r where r.pid="+pId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean stuReplyPost(Student stu, Post post, Reply reply) {
		// TODO Auto-generated method stub
		try{
			reply.setStudent(stu);
			reply.setPost(post);
			reply.setContent(reply.getContent());
			reply.setPublishTime(new Timestamp(new Date().getTime()));
			dao.saveOrUpdate(reply);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modifyReply(Reply reply) {
		// TODO Auto-generated method stub
		try{
			dao.saveOrUpdate(reply);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delReply(int rId) {
		// TODO Auto-generated method stub
		try{
			dao.delById(Reply.class, new Integer(rId));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
