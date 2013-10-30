package com.viapro.elec.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viapro.elec.bean.ElecText;
import com.viapro.elec.dao.ElecTextDao;
import com.viapro.elec.service.ElecTextService;

@Service(ElecTextService.BEAN_NAME)
@Transactional(readOnly=true) //纳入事务管理
public class ElecTextServiceImpl implements ElecTextService {
	
	private ElecTextDao etDao;
	
	@Resource(name=ElecTextDao.BEAN_NAME)
	public void setEtDao(ElecTextDao etDao) {
		this.etDao = etDao;
	}
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, readOnly=false)
	public void saveElecText(ElecText et){
		etDao.save(et);
	}
}
