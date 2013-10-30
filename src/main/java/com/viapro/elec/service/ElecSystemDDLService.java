package com.viapro.elec.service;

import java.util.List;
import java.util.Map;

import com.viapro.elec.bean.ElecSystemDDL;

public interface ElecSystemDDLService {
	
    static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecSystemDDLServiceImpl";

    List<ElecSystemDDL> getAlltype();
    
    List<ElecSystemDDL> getListByKeyword(ElecSystemDDL model);
    
    List<ElecSystemDDL> getListByKeyword(String keyword);
    
    void saveDDlByArray(ElecSystemDDL model);
    
    void deleteByKeyword(ElecSystemDDL model);

		/**
		 * @Name:
		 * @Description:
		 * @Author:ViaPro
		 * @Version:V1.00
		 * @Create Date:2013-10-10 下午6:29:04
		 * @Parameters:
		 * @Return:Map<String,Map<String,String>>
		 */
		Map<String, Map<Integer, String>> getAllDDLMaps();
    
}
