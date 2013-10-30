package com.viapro.elec.service;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

import com.viapro.elec.dao.ElecApplicationDao;
import com.viapro.elec.dao.ElecApplicationTemplateDao;
import com.viapro.elec.dao.ElecApproveInfoDao;
import com.viapro.elec.dao.ElecSystemDDLDao;

public abstract class WorkflowBaseService {
	
	@Resource(name = ElecApplicationDao.BEAN_NAME)
	protected ElecApplicationDao appDao;
	@Resource(name = ElecApplicationTemplateDao.BEAN_NAME)
	protected ElecApplicationTemplateDao atDao;
	@Resource(name = ElecApproveInfoDao.BEAN_NAME)
	protected ElecApproveInfoDao aiDao;
	
	@Resource(name = ElecSystemDDLDao.BEAN_NAME)
	protected ElecSystemDDLDao ddlDao;
	
	@Resource(name = "repositoryService")
	protected RepositoryService repositoryService;
	@Resource(name = "runtimeService")
	protected RuntimeService runtimeService;
	@Resource(name = "taskService")
	protected TaskService taskService;
	@Resource(name = "historyService")
	protected HistoryService historyService;
	@Resource(name = "managementService")
	protected ManagementService managementService;
	
}
