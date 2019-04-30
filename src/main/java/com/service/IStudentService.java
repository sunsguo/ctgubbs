package com.service;

import com.orm.Student;

public interface IStudentService {
	/*
	 * 通过学号查学生
	 */
	public Student getStudentByStuNum(String StuNum);
	/*
	 * 修改学生信息
	 */
	public boolean modifyStudent(Student student);
}
