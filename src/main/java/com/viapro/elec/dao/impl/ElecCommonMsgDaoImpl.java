package com.viapro.elec.dao.impl;

import com.viapro.elec.bean.ElecCommonMsg;
import com.viapro.elec.dao.ElecCommonMsgDao;
import org.springframework.stereotype.Repository;

@Repository(ElecCommonMsgDao.BEAN_NAME)
public class ElecCommonMsgDaoImpl extends BaseDaoImpl<ElecCommonMsg> implements ElecCommonMsgDao {
	
}
