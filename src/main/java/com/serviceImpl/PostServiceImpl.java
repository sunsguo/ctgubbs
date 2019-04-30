package com.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.orm.Admin;
import com.orm.Post;
import com.orm.Student;
import com.service.IPostService;

public class PostServiceImpl implements IPostService {
	@Resource(name="dao")
	BaseDao dao;
	
	@Override
	public boolean saveOrUpdate(Post post) {
		// TODO Auto-generated method stub
		try{
			this.dao.saveOrUpdate(post);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> allPost() {
		// TODO Auto-generated method stub
		return this.dao.listAll("Post");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> pageAllPost(int bid, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.query("from Post as p where p.board.id="+bid+" order by p.publishTime desc", pageNo, pageSize);
	}

	@Override
	public int getPostsCount() {
		// TODO Auto-generated method stub
		return dao.countAll("Post");
	}

	@Override
	public Post loadPost(int id) {
		// TODO Auto-generated method stub
		return (Post)dao.loadById(Post.class, new Integer(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> allPostsByUser(Object user) {
		// TODO Auto-generated method stub
		if(user instanceof Student){
			Student s = (Student)user;
			final String HQL = "from Post as p where p.student.id="+s.getId()+" order by p.publishTime desc";
			List<Post> list = (List<Post>)dao.query(HQL);
			return list;
		}else if(user instanceof Admin){
			Admin a = (Admin)user;
			final String HQL = "from Post as p where p.admin.id="+a.getId()+" order by p.publishTime desc";
			List<Post> list = (List<Post>)dao.query(HQL);
			return list;
		}
		return null;
	}

	@Override
	public boolean deletePost(int id) {
		// TODO Auto-generated method stub
		try{
			dao.delById(Post.class, new Integer(id));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> searchPosts(String key) {
		// TODO Auto-generated method stub
		final String HQL = "from Post as p where p.name like '%"+key+"%'" + " or p.content like '%"+key+"%'";
		return dao.query(HQL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> rankPosts(int size) {
		// TODO Auto-generated method stub
		List<Post> list = dao.query("from Post as p order by p.count,p.id desc");
		List<Post> result = new ArrayList<Post>();
		if(list.size() < size){
			for(int i=0;i<list.size();i++){
				result.add(list.get(i));
			}
		}else{
			for(int i=0;i<size;i++){
				result.add(list.get(i));
			}
		}
		return result;
	}

	@Override
	public int countTotalPost() {
		// TODO Auto-generated method stub
		return dao.countAll("Post");
	}

	@Override
	public int countTodayPost() {
		// TODO Auto-generated method stub
		Date todayDate = new Date();
		Date yesterdayDate = new Date(System.currentTimeMillis() - 1000*60*60*24);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = sf.format(todayDate) ;
		String yesterday = sf.format(yesterdayDate) ;
		final String HQL = "select count(*) from Post as p where p.publishTime between '"+yesterday+"' and '"+today+"'";
		return dao.countQuery(HQL);
	}

	@Override
	public int countYesterdayPost() {
		// TODO Auto-generated method stub
		Date todayDate = new Date();
		Date tomorrowDate = new Date(System.currentTimeMillis() - 1000*60*60*24);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = sf.format(todayDate) ;
		String tomorrow = sf.format(tomorrowDate);
		final String HQL = "select count(*) from Post as p where p.publishTime between '"+today+"' and '"+tomorrow+"'";
		return dao.countQuery(HQL);
	}

	@Override
	public int countDayLargestPost() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("还没有支持");
	}

}
