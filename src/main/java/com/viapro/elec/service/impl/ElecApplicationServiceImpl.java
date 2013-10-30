package com.viapro.elec.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viapro.elec.bean.ElecApplication;
import com.viapro.elec.bean.ElecApplicationTemplate;
import com.viapro.elec.bean.ElecApproveInfo;
import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.service.ElecApplicationService;
import com.viapro.elec.service.WorkflowBaseService;
import com.viapro.elec.util.FileUploadUtil;
import com.viapro.elec.util.QueryBuilder;
import com.viapro.elec.vo.ApplicationVariable;

@Service(ElecApplicationService.BEAN_NAME)
@Transactional(readOnly = true)
public class ElecApplicationServiceImpl extends WorkflowBaseService implements ElecApplicationService {

	@Override
	@Transactional(readOnly = false)
	public void prepareAndStartPD(ElecApplication model) {
		ElecApplicationTemplate at = atDao.getById(model.getApplicationTemplateID());
		// 1. save archive
		String path = FileUploadUtil.saveFile(model.getUpload());
		// 2. save application
		ElecUser user = (ElecUser) ServletActionContext.getContext().getSession().get("globel_user");
		model.setApplicationLogonName(user.getLogonName());
		model.setApplicationUserName(user.getUserName());
		model.setApplicationUserID(user.getUserID());
		Date now = new Date();
		model.setTitle(at.getName() + "_" + user.getUserName() + "_" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now));
		model.setApplyTime(now);
		model.setPath(path);
		model.setStatus(ElecApplication.APPLICATION_RUNNING);
		appDao.save(model);
		// 3. Start a ProcessDefinition instance
		Map<String, Object> params = new HashMap<String, Object>();
		ApplicationVariable modelClone = new ApplicationVariable();
		BeanUtils.copyProperties(model, modelClone);
		params.put("application", modelClone);
		runtimeService.startProcessInstanceByKey(at.getProcessDefinitionKey(), params);
	}

	@Override
	public List<ElecApplication> findAppsByCondition(ElecApplication model) {
		QueryBuilder qb = new QueryBuilder(ElecApplication.class);// from ElecApplication this
		if (model.getApplicationTemplateID() != null)
			qb.addWhereCondition("this.applicationTemplateID=?", model.getApplicationTemplateID());
		if (!StringUtils.isEmpty(model.getStatus()))
			qb.addWhereCondition("this.status=?", model.getStatus());
		List<ElecApplication> apps = appDao.getList(qb.addOrderProperty("this.applyTime", true), 1);
		if (apps != null && apps.size() > 0)
			for (ElecApplication app : apps)
				app.setStatus(ddlDao.getValueByID("审核状态", app.getStatus()));
		return apps;
	}

	@Override
	public List<ElecApplication> findMyTasks() {
		List<ElecApplication> apps = null;
		String assignee = ((ElecUser) ServletActionContext.getContext().getSession().get("globel_user")).getLogonName();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
		if (tasks != null && tasks.size() > 0) {
			apps = new ArrayList<ElecApplication>();
			for (Task task : tasks) {
				ApplicationVariable variable = (ApplicationVariable) taskService.getVariable(task.getId(), "application");
				ElecApplication app = new ElecApplication();
				BeanUtils.copyProperties(variable, app);
				app.setTaskId(task.getId());
				apps.add(app);
			}
		}
		return apps;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean approveTask(ElecApplication model) {
		boolean done = false;
		ElecApplication app = appDao.getById(model.getApplicationID());
		persistApproveInfo(model);
		Task task = taskService.createTaskQuery().taskId(model.getTaskId()).singleResult();
		String executionId = task.getExecutionId();
		taskService.complete(model.getTaskId());
		Execution execution = runtimeService.createExecutionQuery().executionId(executionId).singleResult();
		if (model.isApproval()) {
			if (execution == null)
				app.setStatus(ElecApplication.APPLICATION_PASS);
		} else {
			if (execution != null)
				runtimeService.deleteProcessInstance(executionId, model.getComment());
				// runtimeService.suspendProcessInstanceById(executionId);//有待商榷的步骤，是否能够真的结束流程？
			app.setStatus(ElecApplication.APPLICATION_REJECT);
		}
		return done;
	}

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-9 PM7:45:46
	 * @Parameters:
	 * @Return:void
	 */
	@Transactional(readOnly = false)
	private void persistApproveInfo(ElecApplication model) {
		ElecUser user = (ElecUser) ServletActionContext.getContext().getSession().get("globel_user");
		ElecApproveInfo ai = new ElecApproveInfo();
		ai.setApplicationID(model.getApplicationID());
		ai.setComment(model.getComment());
		ai.setApproval(model.isApproval());
		ai.setApproveUserID(user.getUserID());
		ai.setApproveUserName(user.getUserName());
		ai.setApproveTime(new Date());
		aiDao.save(ai);
	}

	@Override
	public boolean getApplicationResource(ElecApplication model) {
		ElecApplication app = appDao.getById(model.getApplicationID());
		try {
			model.setTitle(app.getTitle());
			model.setInputStream(new FileInputStream(app.getPath()));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

}
