package com.viapro.elec.service;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;

import com.viapro.elec.vo.ProcessDefinitionBean;

public interface ElecProcessDefinitionService {
	public static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecProcessDefinitionServiceImpl";

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-5 PM8:20:40
	 * @Parameters:
	 * @Return:List<ProcessDefinition>
	 */
	List<ProcessDefinition> getProcessDefinition4New();

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-5 PM9:12:07
	 * @Parameters:
	 * @Return:void
	 */
	void deletePDById4AllVersion(String id);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-6 AM9:47:31
	 * @Parameters:
	 * @Return:void
	 */
	void deployPD(ProcessDefinitionBean model);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-6 下午1:07:47
	 * @Parameters:
	 * @Return:InputStream
	 */
	InputStream getImageResourceById4New(String processDefinitionId);
	
}
