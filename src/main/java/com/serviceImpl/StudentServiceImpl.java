package com.serviceImpl;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.orm.Student;
import com.service.IStudentService;

public class StudentServiceImpl implements IStudentService {
	@Resource(name="dao")
	BaseDao dao;
	
	@Override
	public Student getStudentByStuNum(String stuNum) {
		// TODO Auto-generated method stub
		return (Student)dao.loadObject("from Student as s where s.stuNum='"+stuNum+"'");
	}

	@Override
	public boolean modifyStudent(Student student) {
		// TODO Auto-generated method stub
		try{
			dao.saveOrUpdate(student);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
