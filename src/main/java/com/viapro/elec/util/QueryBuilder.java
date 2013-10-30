package com.viapro.elec.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class QueryBuilder {

    private String selectClause;
    private String fromClause;
    private String whereClause;
    private String orderByClause;

    private List<Object> parameters = new ArrayList<Object>();

    /**
     * 生成 FROM 子句
     *
     * @param entityClass
     */
    @SuppressWarnings("rawtypes")
    public QueryBuilder(Class entityClass) {
        fromClause = "FROM " + entityClass.getName() + " this";
    }


    /**
     * 拼接 SELECT 子句
     *
     * @param property
     */
    public QueryBuilder addSelectProperty(String property) {
        if (selectClause == null) {
            selectClause = "SELECT " + property;
        } else {
            selectClause += " ," + property;
        }
        return this;
    }

    /**
     * 拼接 WHERE 子句
     *
     * @param condition
     * @param params
     */
    public QueryBuilder addWhereCondition(String condition, Object... params) {
        if (whereClause == null) {
            whereClause = "WHERE " + condition;
        } else {
            whereClause += " AND " + condition;
        }
        // 保存参数
        if (params != null && params.length > 0) {
            for (Object param : params) {
                parameters.add(param);
            }
        }
        return this;
    }

    /**
     * 拼接 ORDER BY 子句
     *
     * @param property
     * @param reverse
     */
    public QueryBuilder addOrderProperty(String property, boolean reverse) {
        if (orderByClause == null) {
            orderByClause = "ORDER BY " + property + (reverse ? " DESC" : " ASC");
        } else {
            orderByClause += " ," + property + (reverse ? " DESC" : " ASC");
        }
        return this;
    }

    /**
     * 生成查询总数量的HQL
     *
     * @return
     */
    public String toQueryCountHql() {
        StringBuffer hql = new StringBuffer();

        hql.append("SELECT COUNT(*) ");
        addClauseIfNotNull(hql, fromClause);
        addClauseIfNotNull(hql, whereClause);
        // 查询数量不需要排序
        return hql.toString();
    }

    /**
     * 生成查询数据列表的HQL
     *
     * @return
     */
    public String toQueryListHql() {
        StringBuffer hql = new StringBuffer();

        addClauseIfNotNull(hql, selectClause);
        addClauseIfNotNull(hql, fromClause);
        addClauseIfNotNull(hql, whereClause);
        addClauseIfNotNull(hql, orderByClause);

        System.out.println(hql.toString());

        return hql.toString();
    }

    private void addClauseIfNotNull(StringBuffer hql, String clause) {
        if (clause != null) {
            hql.append(clause).append(" ");
        }
    }

    /**
     * 查询总记录数
     *
     * @param session
     * @return
     */
    public int queryCount(Session session) {
        Query countQuery = session.createQuery(toQueryCountHql());
        for (int i = 0; i < parameters.size(); i++) {// 设置参数
            countQuery.setParameter(i, parameters.get(i));
        }
        return ((Number) countQuery.uniqueResult()).intValue(); // 查询数量
    }

    /**
     * 查询指定页的数据
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List queryList(Session session, int pageNum, int pageSize) {
        Query query = session.createQuery(toQueryListHql());
        for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
        }
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * 查询指定页的数据
     *
     * @return
     */
    public List queryListUseCache(Session session, int pageNum, int pageSize) {
        Query listQuery = session.createQuery(toQueryListHql());
        for (int i = 0; i < parameters.size(); i++) {// 设置参数
            listQuery.setParameter(i, parameters.get(i));
        }
        listQuery.setCacheable(true);//开启二级缓存
        listQuery.setFirstResult((pageNum - 1) * pageSize);
        listQuery.setMaxResults(pageSize);
        return listQuery.list();
    }

    /**
     * 查询指定页的数据
     *
     * @return
     */
/*	public List queryList(HibernateTemplate tt,final int pageNum,final int pageSize) {

		return tt.execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query listQuery = session.createQuery(toQueryListHql());
				for (int i = 0; i < parameters.size(); i++) {// 设置参数
					listQuery.setParameter(i, parameters.get(i));
				}

				listQuery.setFirstResult((pageNum - 1) * pageSize);
				listQuery.setMaxResults(pageSize);
				return listQuery.list();				
			}
		});
		
		retuen null;
	}*/
}
