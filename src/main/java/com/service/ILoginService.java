package com.service;

import com.orm.Admin;
import com.orm.Student;

public interface ILoginService {
	/*
	 * 学生登陆
	 */
	public Student stuLogin(Student student);
	/*
	 * 管理员登陆
	 */
	public Admin adminLogin(Admin admin);
	/**
	 * 注册新用户
	 */
	public boolean register(Student student);
}
