package com.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface BaseDao {
	/**
	 * 加载指定id的持久化对象
	 */
	public Object loadById(Class clazz, Serializable id);
	/**
	 * 加载满足条件的持久化对象
	 */
	public Object loadObject(String hql);
	/**
	 * 删除指定id的持久化对象
	 */
	public void delById(Class clazz, Serializable id);
	/**
	 * 保存或跟新指定持久化对象
	 */
	public void saveOrUpdate(Object obj);
	/**
	 * 装载指定类的所有持久化对象
	 */
	public List listAll(String clazz);
	/**
	 * 分页装载指定类的所有持久化对象
	 */
	public List listAll(String clazz, int pageNo, int pageSize);
	/**
	 * 统计指定类的所有持久化对象
	 */
	public int countAll(String clazz);
	/**
	 * 查询指定类的满足条件的持久化对象
	 */
	public List query(String hql);
	/**
	 * 分页查询指定类的满足条件的持久化对象
	 */
	public List query(String hql, int pageNo, int pageSize);
	/**
	 * 统计指定类的查询结果
	 */
	public int countQuery(String sql);
	/**
	 * 条件更新数据库
	 */
	public int update(String hql);
	/**
	 * 从链接池获取jdbc链接
	 */
	public Connection getConnection();
	
	
}
