package com.serviceImpl;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.orm.Admin;
import com.orm.Student;
import com.service.ILoginService;

public class LoginServiceImpl implements ILoginService {
	@Resource(name="dao")
	BaseDao dao;

	@Override
	public Student stuLogin(Student student) {
		// TODO Auto-generated method stub
		final String hql = "from Student as s where s.stuNum='" + student.getStuNum() + "' and s.password='"+student.getPassword()+"'";
		Student stu = (Student)dao.loadObject(hql);
		return stu;
	}

	@Override
	public Admin adminLogin(Admin admin) {
		// TODO Auto-generated method stub
		final String hql = "from Admin as a where a.account='" + admin.getAccount() + "' and a.password='"+admin.getPassword()+"'";
		Admin a = (Admin)dao.loadObject(hql);
		return a;
	}
	
	@Override
	public boolean register(Student student) {
		// TODO Auto-generated method stub
		try{
			this.dao.saveOrUpdate(student);
			return true;
		}catch(Exception e){
			e.printStackTrace( );
			return false;
		}
	}
	
}
