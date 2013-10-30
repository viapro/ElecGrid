package com.viapro.elec.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.dao.BaseDao;
import com.viapro.elec.dao.ElecApproveInfoDao;
import com.viapro.elec.util.PageView;
import com.viapro.elec.util.QueryBuilder;

@SuppressWarnings("unchecked")
// 纳入事务管理，仅仅为Hibernate4的测试而用
public class BaseDaoImpl<T extends Serializable> implements BaseDao<T> {

	// protected Logger log = Logger.getLogger(BaseDaoImpl.class);

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sf) {
		sessionFactory = sf;
		// super.setSessionFactory(sf);
	}

	protected Class<T> clazz;

	public BaseDaoImpl() {
		// 获得超类的泛型参数的实际类型
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T obj) {
		getSession().save(obj);
	}

	public void delete(Serializable id) {
		Object obj = getSession().get(clazz, id);
		if (obj != null)
			getSession().delete(obj);
	}

	public void update(T obj) {
		getSession().update(obj);
	}

	public void saveOrUpdate(T obj) {
		getSession().saveOrUpdate(obj);
	}

	public T getById(Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIdList(final Long[] idList) {
		if (idList == null || idList.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery(new StringBuilder("FROM ").append(clazz.getName()).append(" WHERE id in (:idList)").toString()).setParameterList("idList", idList).list();
		}
	}

	public List<T> getAll() {
		return getSession().createQuery(new StringBuilder("FROM ").append(clazz.getName()).toString()).list();
	}

	public PageView getPageView(QueryBuilder queryBuilder, int pageNum) {
		int pageSize = 10;
		int count = queryBuilder.queryCount(getSession());
		List<T> list = queryBuilder.queryList(getSession(), pageNum, pageSize);
		return new PageView(pageNum, pageSize, count, list);
	}

	public List<T> getList(QueryBuilder queryBuilder, int pageNum) {
		int pageSize = Integer.MAX_VALUE;
		// int count = queryBuilder.queryCount(getSession());
		List<T> list = queryBuilder.queryList(getSession(), pageNum, pageSize);
		return list;
	}

	public List<T> getListUseCache(QueryBuilder queryBuilder, int pageNum) {
		int pageSize = Integer.MAX_VALUE;
		// int count = queryBuilder.queryCount(getSession());
		List<T> list = queryBuilder.queryListUseCache(getSession(), pageNum, pageSize);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getListWithoutGen(QueryBuilder queryBuilder, int pageNum) {
		return this.getList(queryBuilder, pageNum);
	}

	public static void main(String[] args) {
		ElecApproveInfoDao dao = new ElecApproveInfoDaoImpl();
		System.out.println();
	}
}