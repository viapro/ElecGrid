package com.viapro.elec.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.viapro.elec.bean.ElecApplication;
import com.viapro.elec.bean.ElecApproveInfo;
import com.viapro.elec.service.ElecApplicationService;
import com.viapro.elec.service.ElecApplicationTemplateService;
import com.viapro.elec.service.ElecApproveInfoService;
import com.viapro.elec.service.ElecSystemDDLService;

@Controller("elecApplicationFlowAction")
@Scope("prototype")
public class ElecApplicationFlowAction extends BaseAction<ElecApplication> {
	private static final long serialVersionUID = 1L;

	ElecApplicationTemplateService atService;
	ElecApplicationService appService;
	ElecApproveInfoService aiService;
	ElecSystemDDLService ddlService;

	@Resource(name = ElecApplicationService.BEAN_NAME)
	public void setAppService(ElecApplicationService appService) {
		this.appService = appService;
	}

	@Resource(name = ElecApplicationTemplateService.BEAN_NAME)
	public void setAtService(ElecApplicationTemplateService atService) {
		this.atService = atService;
	}

	@Resource(name = ElecApproveInfoService.BEAN_NAME)
	public void setAiService(ElecApproveInfoService aiService) {
		this.aiService = aiService;
	}

	@Resource(name = ElecSystemDDLService.BEAN_NAME)
	public void setDdlService(ElecSystemDDLService ddlService) {
		this.ddlService = ddlService;
	}

	/**
	 * @Name:home
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-8 PM1:35:14
	 * @Parameters:
	 * @Return:String
	 */
	public String home() {
		// prepare all templates
		request.setAttribute("ats", atService.findAll());
		return "home";
	}

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-8 PM1:22:00
	 * @Parameters:
	 * @Return:/WEB-INF/page/workflow/flowTemplateList.jsp
	 */
	public String submitApplication() {
		return "submitApplication";
	}

	/**
	 * @Name:saveApplication
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-8 PM1:31:59
	 * @Parameters:
	 * @Return:String
	 */
	public String saveApplication() {
		appService.prepareAndStartPD(model);
		return "saveApplication";
	}

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-8 PM4:43:26
	 * @Parameters:
	 * @Return:String
	 */
	public String myApplicationHome() {
		request.setAttribute("apps", appService.findAppsByCondition(model));
		request.setAttribute("ats", atService.findAll());
		request.setAttribute("ddls", ddlService.getListByKeyword("审核状态"));
		return "myApplicationHome";
	}

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-8 PM6:21:54
	 * @Parameters:
	 * @Return:String
	 */
	public String approvedHistory() {
		model.getApplicationID();
		List<ElecApproveInfo> ais = aiService.findApprovedHistory(model); // 等审批的流程完成后来继续这个模块
		request.setAttribute("ais", ais);
		return "approvedHistory";
	}

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-8 PM7:44:36
	 * @Parameters:
	 * @Return:String
	 */
	public String myTaskHome() {
		request.setAttribute("apps", appService.findMyTasks());
		request.setAttribute("outcomes", null);
		return "myTaskHome";
	}

	/**
	 * @Name:flowApprove
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-9 PM3:37:29
	 * @Parameters:
	 * @Return:String
	 */
	public String flowApprove() {
		return "flowApprove";
	}

	/**
	 * @Name:download
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-9 PM4:04:38
	 * @Parameters:
	 * @Return:String
	 */
	public String download() {
		boolean done = appService.getApplicationResource(model);
		return "download";
	}

	/**
	 * @Name:approve
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-9 PM4:10:52
	 * @Parameters:
	 * @Return:String
	 */
	public String approve() {
		boolean done = appService.approveTask(model);
		return "approve";
	}
}
