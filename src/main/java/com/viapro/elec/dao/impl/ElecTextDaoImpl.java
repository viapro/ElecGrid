package com.viapro.elec.dao.impl;

import org.springframework.stereotype.Repository;

import com.viapro.elec.bean.ElecText;
import com.viapro.elec.dao.ElecTextDao;

@Repository(ElecTextDao.BEAN_NAME)	//把dao纳入到spring容器的管理中
public class ElecTextDaoImpl extends BaseDaoImpl<ElecText> implements ElecTextDao {

}
