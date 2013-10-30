package com.viapro.elec.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viapro.elec.service.ElecProcessDefinitionService;
import com.viapro.elec.vo.ProcessDefinitionBean;

@Service(ElecProcessDefinitionService.BEAN_NAME)
@Transactional(readOnly = true)
public class ElecProcessDefinitionServiceImpl implements ElecProcessDefinitionService {

	RepositoryService repositoryService;

	@Resource(name = "repositoryService")
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	@Override
	// @Transactional(readOnly = false) //临时代码！！！！！！！！！！！！
	public List<ProcessDefinition> getProcessDefinition4New() {
		// repositoryService.createDeployment().addClasspathResource("VacationRequest.bpmn20.xml").deploy();
		return repositoryService.createProcessDefinitionQuery().latestVersion().list();
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePDById4AllVersion(String id) {
		if (!StringUtils.isEmpty(id))
			repositoryService.deleteDeployment(id, true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.viapro.elec.service.ElecProcessDefinitionService#deployPD(com.viapro.elec.vo.ProcessDefinitionBean)
	 */
	@Override
	@Transactional(readOnly=false)
	public void deployPD(ProcessDefinitionBean model) {
		try {
			repositoryService.createDeployment().addZipInputStream(new ZipInputStream(new FileInputStream(model.getUpload()))).deploy();
		} catch (FileNotFoundException e) {
			System.out.println("部署文件错误！");
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.service.ElecProcessDefinitionService#getImageResourceById4New(java.lang.String)
	 */
	@Override
	public InputStream getImageResourceById4New(String processDefinitionId) {
		return repositoryService.getProcessDiagram(processDefinitionId);
	}

}
