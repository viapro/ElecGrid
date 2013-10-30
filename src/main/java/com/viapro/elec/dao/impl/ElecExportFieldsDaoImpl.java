package com.viapro.elec.dao.impl;

import org.springframework.stereotype.Repository;

import com.viapro.elec.bean.ElecExportFields;
import com.viapro.elec.dao.ElecExportFieldsDao;

@Repository(ElecExportFieldsDao.BEAN_NAME)
public class ElecExportFieldsDaoImpl extends BaseDaoImpl<ElecExportFields> implements ElecExportFieldsDao {
	
	@Override
	public ElecExportFields getById(String userID, String belongTo) {
		return (ElecExportFields) getSession()
				.createQuery("from ElecExportFields e where e.userID=? and e.belongTo=?")
				.setParameter(0, userID).setParameter(1, belongTo).uniqueResult();
	}

}
