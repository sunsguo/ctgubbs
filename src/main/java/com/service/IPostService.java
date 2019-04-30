package com.service;

import java.util.List;

import com.orm.Post;

public interface IPostService {
	/*
	 * 跟新帖子
	 */
	public boolean saveOrUpdate(Post post);
	/*
	 * 获得所有帖子
	 */
	public List<Post> allPost();
	/*
	 * 获得每一页的帖子
	 */
	public List<Post> pageAllPost(int bid, int pageNo, int pageSize);
	/*
	 * 获得所有帖子的总数
	 */
	public int getPostsCount();
	/*
	 * 根据id加载帖子
	 */
	public Post loadPost(int id);
	/*
	 * 加载每个用户的帖子
	 */
	public List<Post> allPostsByUser(Object user);
	/*
	 * 删除帖子
	 */
	public boolean deletePost(int id);
	/*
	 * 通过关键字查找帖子
	 */
	public List<Post> searchPosts(String key);
	/*
	 * 获得size条热帖
	 */
	public List<Post> rankPosts(int size);
	/*
	 * 获得总帖子数
	 */
	public int countTotalPost();
	/*
	 * 获得今天帖子数
	 */
	public int countTodayPost();
	/*
	 * 统计昨天帖子数
	 */
	public int countYesterdayPost();
	/*
	 * 某天最大发条数
	 */
	public int countDayLargestPost();
}
