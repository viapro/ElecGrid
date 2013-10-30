package com.viapro.elec.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viapro.elec.bean.ElecSystemDDL;
import com.viapro.elec.dao.ElecSystemDDLDao;
import com.viapro.elec.service.ElecSystemDDLService;
import com.viapro.elec.util.QueryBuilder;

@Service(ElecSystemDDLService.BEAN_NAME)
@Transactional
public class ElecSystemDDLServiceImpl implements ElecSystemDDLService {
	private ElecSystemDDLDao ddlDao;

	@Resource(name = ElecSystemDDLDao.BEAN_NAME)
	public void setDdlDao(ElecSystemDDLDao ddlDao) {
		this.ddlDao = ddlDao;
	}

	public List<ElecSystemDDL> getAlltype() {
		// 期望的hql： select distinct this.keyword from ElecSystemDDL this from ElecSystemDDL this
		QueryBuilder qb = new QueryBuilder(ElecSystemDDL.class);
		// select distinct this.keyword from ElecSystemDDL this
		qb.addSelectProperty(" distinct this.keyword ");
		@SuppressWarnings("rawtypes")
		List list = ddlDao.getList(qb, 1);
		List<ElecSystemDDL> ddlList = new ArrayList<ElecSystemDDL>();
		// 因为是属性查询，返回的应该是object的集合，要进行转换，使代码更严谨
		for (Object o : list) {
			ElecSystemDDL ddl = new ElecSystemDDL();
			ddl.setKeyword(o.toString());
			ddlList.add(ddl);
		}
		return ddlList;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveDDlByArray(ElecSystemDDL model) {
		String[] itemNames = model.getItemname();
		String keyword = model.getKeywordname();
		int flag = 1;
		for (String itemName : itemNames) {
			ElecSystemDDL ddl = new ElecSystemDDL();
			ddl.setKeyword(keyword);
			ddl.setDdlCode(flag++);
			ddl.setDdlName(itemName);
			ddlDao.save(ddl);
		}
	}

	public void deleteByKeyword(ElecSystemDDL model) {
		// delete ElecSystemDDL this where this.keyword = ?
		// String hql = "delete "+model.getClass().getName()+" this where this.keyword = ?";
		// 1通过 keyword 查询出所有 对应实体
		List<ElecSystemDDL> list = this.getListByKeyword(model);
		// 2 将实体删除
		for (ElecSystemDDL ddl : list) {
			ddlDao.delete(ddl.getSeqID());
		}
	}

	public List<ElecSystemDDL> getListByKeyword(ElecSystemDDL model) {
		// from ElecSystemDDL this where this.keyword = ? order by ddlCode asc
		if (!StringUtils.isEmpty(model.getKeyword())) {
			return this.getListByKeyword(model.getKeyword());
		}
		if (!StringUtils.isEmpty(model.getKeywordname())) {
			return this.getListByKeyword(model.getKeywordname());
		}
		return null;
	}

	public List<ElecSystemDDL> getListByKeyword(String keyword) {
		QueryBuilder qb = new QueryBuilder(ElecSystemDDL.class);
		if (!StringUtils.isEmpty(keyword)) {
			qb.addWhereCondition(" keyword = ? ", keyword);
		}
		qb.addOrderProperty(" ddlCode ", false);
		return ddlDao.getListUseCache(qb, 1);
	}

	@Override
	public Map<String, Map<Integer, String>> getAllDDLMaps() {
		QueryBuilder qb = new QueryBuilder(ElecSystemDDL.class);
		List<ElecSystemDDL> ddls = ddlDao.getList(qb, 1);
		Map<String, Map<Integer, String>> ddlMaps = null;
		if (ddls != null && ddls.size() > 0) {
			ddlMaps = new HashMap<String, Map<Integer, String>>();
			for (ElecSystemDDL ddl : ddls)
				if (ddlMaps.containsKey(ddl.getKeyword())) {
					ddlMaps.get(ddl.getKeyword()).put(ddl.getDdlCode(), ddl.getDdlName());
				} else {
					Map<Integer, String> code2NameMap = new HashMap<Integer, String>();
					code2NameMap.put(ddl.getDdlCode(), ddl.getDdlName());
					ddlMaps.put(ddl.getKeyword(), code2NameMap);
				}
		}
		return ddlMaps;
	}
}
