package com.viapro.elec.dao;

import java.io.Serializable;
import java.util.List;

import com.viapro.elec.util.PageView;
import com.viapro.elec.util.QueryBuilder;

public interface BaseDao<T extends Serializable> {

	/**
	 * 保存
	 * 
	 * @param obj  aaa  aaa
	 */
	void save(T obj);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void delete(Serializable id);

	/**
	 * 更新
	 * 
	 * @param obj
	 */
	void update(T obj);

	/**
	 * 保存或更新
	 * 
	 * @param obj
	 */
	void saveOrUpdate(T obj);

	/**
	 * 获取实体
	 * 
	 * @param id
	 * @return T
	 */
	T getById(Serializable id);

	/**
	 * 获取实体
	 * 
	 * @param idList
	 * @return List<T>
	 */
	List<T> getByIdList(Long[] idList);

	/**
	 * 查询所有
	 * 
	 * @return List<T>
	 */
	List<T> getAll();

	/**
	 * 获得分页数据
	 * 
	 * @return PageView
	 */

	PageView getPageView(QueryBuilder queryBuilder, int pageNum);

	/**
	 * 获得根据查询条件查出list
	 * 
	 * @return List<T>
	 */
	List<T> getList(QueryBuilder queryBuilder, int pageNum);

	/**
	 * 获得根据查询条件查出list使用二级缓存
	 * 
	 * @return
	 */
	List<T> getListUseCache(QueryBuilder queryBuilder, int pageNum);

	/**
	 * 获得根据查询条件查出list
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List getListWithoutGen(QueryBuilder queryBuilder, int pageNum);
}
