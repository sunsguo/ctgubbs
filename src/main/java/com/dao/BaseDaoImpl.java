package com.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("deprecation")
public class BaseDaoImpl implements BaseDao {
	/**
	 * 通过spring的bean管理机制获取对象
	 */
	@Resource(name="hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;
	/**
	 * 加载指定id对象
	 * (non-Javadoc)
	 * @see BaseDao#loadById(Class, Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object loadById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		 this.hibernateTemplate.setCacheQueries(true);
		 return this.hibernateTemplate.get(clazz, id);
	}
	/**	
	 * 根据HQL查找指定对象
	 * (non-Javadoc)
	 * @see BaseDao#loadObject(String)
	 */
	@Override
	public Object loadObject(String hql) {
		// TODO Auto-generated method stub
		final String HQL = hql;
		Object object = null;
		List list = (List) this.hibernateTemplate.execute(new HibernateCallback<List>() {

			@Override
			public List doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery(HQL).setCacheable(true);
				return query.list();
			}
		});
		if(list.size() > 0){
			object = list.get(0);
		}
		return object;
	}
	/*
	 * 通过id删除对象
	 * (non-Javadoc)
	 * @see com.dao.BaseDao#delById(java.lang.Class, java.io.Serializable)
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void delById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.delete(this.hibernateTemplate.load(clazz, id));
	}
	/*
	 * 保存或更新
	 * (non-Javadoc)
	 * @see com.dao.BaseDao#saveOrUpdate(java.lang.Object)
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void saveOrUpdate(Object obj) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.saveOrUpdate(obj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listAll(String clazz) {
		// TODO Auto-generated method stub
		final String HQL = "from " + clazz + " as c" ;
		return (List) this.hibernateTemplate.execute(new HibernateCallback<List>() {

			
			@Override
			public List doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery(HQL).setCacheable(true);
				return query.list();
			}
		});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listAll(String clazz, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		final int PAGENO = pageNo;
		final int PAGESIZE = pageSize;
		final String HQL = "from " + clazz + " as c order by c.id";
		final String CLAZZ = clazz;
		@SuppressWarnings("unchecked")
		List list = (List) this.hibernateTemplate.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(HQL).setCacheable(true);
				query.setMaxResults(PAGESIZE);
				query.setFirstResult((PAGENO-1)*PAGESIZE);
				List result = query.list();
				if(!Hibernate.isInitialized(result)){
					Hibernate.isInitialized(result);
				}
				return result;
			}
		});
		return list;
	}

	@SuppressWarnings( { "deprecation", "unchecked" } )
	@Override
	public int countAll(String clazz) {
		// TODO Auto-generated method stub
		final String HQL = "select count(*) from "+ clazz + " as c";
		Long count = (Long) this.hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(HQL);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}
	/*
	 * 查询指定类的满足条件的持久化对象
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List query(String hql) {
		// TODO Auto-generated method stub
		final String HQL = hql;
		return (List) this.hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(HQL).setCacheable(true);
				return query.list();
			}
		});
	}
	/*
	 * 分页查询指定类的满足条件的持久化对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List query(String hql, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		final int PAGENO = pageNo;
		final int PAGESIZE = pageSize;
		final String HQL = hql;
		List list = (List) this.hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(HQL).setCacheable(true);
				query.setMaxResults(PAGESIZE);
				query.setFirstResult((PAGENO-1)*PAGESIZE);
				List result = query.list();
				if(!Hibernate.isInitialized(result)){
					Hibernate.isInitialized(result);
				}
				return result;
			}
		});
		return list;
	}
	/*
	 * 统计指定类的查询结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int countQuery(String sql) {
		// TODO Auto-generated method stub
		final String HQL = sql;
		Long count = (Long) this.hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(HQL);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@Override
	public int update(String hql) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("还没有支持");
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("还没有支持");
	}

}
