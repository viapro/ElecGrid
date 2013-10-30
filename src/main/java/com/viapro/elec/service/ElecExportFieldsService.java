package com.viapro.elec.service;

import com.viapro.elec.bean.ElecExportFields;

public interface ElecExportFieldsService {
    static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecExportFieldsServiceImpl";

	void save(ElecExportFields model);

	ElecExportFields getElecExportFields(ElecExportFields model);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-11 下午2:16:14
	 * @Parameters:
	 * @Return:ElecExportFields
	 */
	ElecExportFields getElecExportFieldsByBelongToAndCurrentUserID(String belongTo);
}
