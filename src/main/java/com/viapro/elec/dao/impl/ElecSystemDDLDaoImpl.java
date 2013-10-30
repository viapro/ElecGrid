package com.viapro.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.viapro.elec.bean.ElecSystemDDL;
import com.viapro.elec.dao.ElecSystemDDLDao;
import com.viapro.elec.util.QueryBuilder;

@Repository(ElecSystemDDLDao.BEAN_NAME)
public class ElecSystemDDLDaoImpl extends BaseDaoImpl<ElecSystemDDL> implements ElecSystemDDLDao {

	public String getValueByID(String keyword, String ddlCode) {
		// String hql = "from ElecSystemDDL this where this.keyword = ? and this.ddlCode = ?";
		QueryBuilder qb = new QueryBuilder(ElecSystemDDL.class);
		qb.addWhereCondition("keyword = ?", keyword).addWhereCondition("ddlCode = ?", Integer.valueOf(ddlCode));
		List<ElecSystemDDL> list = this.getList(qb, 1);
		if (list != null && list.size() > 0) {
			return list.get(0).getDdlName();
		}
		return null;
	}

	public Integer getDDLNameByKeyWordAndDDCode(String string, String name) {
		// String hql = "from ElecSystemDDL this where this.keyword = ? and this.ddlName = ?";
		QueryBuilder qb = new QueryBuilder(ElecSystemDDL.class);
		qb.addWhereCondition("keyword = ?", string).addWhereCondition("ddlName = ?", name);
		List<ElecSystemDDL> list = this.getList(qb, 1);
		if (list != null && list.size() > 0) {
			return list.get(0).getDdlCode();
		}
		return null;
	}

}
