package com.viapro.elec.service;

import com.viapro.elec.bean.ElecText;

public interface ElecTextService {
	public static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecTextServiceImpl";
	void saveElecText(ElecText et);
}
