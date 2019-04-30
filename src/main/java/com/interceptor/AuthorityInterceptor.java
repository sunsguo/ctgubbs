package com.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.orm.Admin;
import com.orm.Student;

public class AuthorityInterceptor extends MethodFilterInterceptor {
	
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		ActionContext ac = arg0.getInvocationContext();
		Map session = ac.getSession();
		if(session.get("student") == null && session.get("admin") == null){
			return Action.LOGIN;
		}else{
			Student stu = (Student)session.get("student");
			Admin admin = (Admin)session.get("admin");
			if(stu != null){
				if(stu.getNickName() == null){
					return "stuPersonalInfo";
				}
				return arg0.invoke();
			}
			if(admin != null){
				if(admin.getNickName() == null){
					return "adminPersonalInfo";
				}
				return arg0.invoke();
			}
		}
		return Action.LOGIN;
	}
	
}
