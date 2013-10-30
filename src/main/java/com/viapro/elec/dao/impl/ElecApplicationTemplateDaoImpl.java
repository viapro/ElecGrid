package com.viapro.elec.dao.impl;

import org.springframework.stereotype.Repository;

import com.viapro.elec.bean.ElecApplicationTemplate;
import com.viapro.elec.dao.ElecApplicationTemplateDao;

@Repository(ElecApplicationTemplateDao.BEAN_NAME)	//把dao纳入到spring容器的管理中
public class ElecApplicationTemplateDaoImpl extends BaseDaoImpl<ElecApplicationTemplate> implements ElecApplicationTemplateDao {

}
