package com.viapro.elec.dao.impl;

import org.springframework.stereotype.Repository;

import com.viapro.elec.bean.ElecApplication;
import com.viapro.elec.dao.ElecApplicationDao;

@SuppressWarnings("unchecked")
@Repository(ElecApplicationDao.BEAN_NAME)
public class ElecApplicationDaoImpl extends BaseDaoImpl<ElecApplication> implements ElecApplicationDao {
	static{
		System.out.println("########################################################################################################################################################################################################################################################################################################################");
	}

}
