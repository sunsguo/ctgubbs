package com.tools;

import java.lang.reflect.Member;
import java.util.Map;

import ognl.DefaultTypeConverter;

public class StringToInteger extends DefaultTypeConverter {
	
	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		// TODO Auto-generated method stub
		if(toType == String.class){
			return Integer.parseInt((String) value);
		}
		return (Integer)value;
	}
}
