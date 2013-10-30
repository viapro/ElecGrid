package com.viapro.elec.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viapro.elec.bean.ElecExportFields;
import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.dao.ElecExportFieldsDao;
import com.viapro.elec.service.ElecExportFieldsService;
import com.viapro.elec.util.QueryBuilder;

@Service(ElecExportFieldsService.BEAN_NAME)
@Transactional
public class ElecExportFieldsServiceImpl implements ElecExportFieldsService {

	private ElecExportFieldsDao elecExportFieldsDao;

	@Resource(name = ElecExportFieldsDao.BEAN_NAME)
	public void setElecExportFieldsDao(ElecExportFieldsDao elecExportFieldsDao) {
		this.elecExportFieldsDao = elecExportFieldsDao;
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED,readOnly=false)
	public void save(ElecExportFields model) {
		// TODO Auto-generated method stub
		elecExportFieldsDao.update(model);
	}

	@Override
	public ElecExportFields getElecExportFields(ElecExportFields model) {
		System.out.println(model);
		if(StringUtils.isEmpty(model.getBelongTo())){
			return null;
		}
		return elecExportFieldsDao.getById(model.getUserID(),model.getBelongTo());
	}

	@Override
	public ElecExportFields getElecExportFieldsByBelongToAndCurrentUserID(String belongTo) {
		ElecUser user = (ElecUser) ServletActionContext.getContext().getSession().get("globel_user");
		QueryBuilder qb = new QueryBuilder(ElecUser.class).addWhereCondition("this.userID=?", user.getUserID()).addWhereCondition("this.belongTo=?", belongTo);
		return elecExportFieldsDao.getList(qb, 1).get(0);
	}
	


}
