package com.viapro.elec.dao;

import com.viapro.elec.bean.ElecExportFields;

public interface ElecExportFieldsDao extends BaseDao<ElecExportFields> {
    static final String BEAN_NAME = "com.viapro.elec.dao.impl.ElecExportFieldsDaoImpl";

	ElecExportFields getById(String userID, String belongTo);
}
