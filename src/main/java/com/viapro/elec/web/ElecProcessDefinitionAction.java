package com.viapro.elec.web;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.viapro.elec.service.ElecProcessDefinitionService;
import com.viapro.elec.vo.ProcessDefinitionBean;

@Controller("elecProcessDefinitionAction")
@Scope("prototype")
public class ElecProcessDefinitionAction extends BaseAction<ProcessDefinitionBean> {
	private static final long serialVersionUID = 1L;

	ElecProcessDefinitionService pdService;

	@Resource(name = ElecProcessDefinitionService.BEAN_NAME)
	public void setPdService(ElecProcessDefinitionService pdService) {
		this.pdService = pdService;
	}

	/**
	 * @Name:home
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-5 PM2:49:14
	 * @Parameters:
	 * @Return:
	 */
	public String home() {
		List<ProcessDefinition> pds = pdService.getProcessDefinition4New();
		if(pds!=null && pds.size()>0){
			request.setAttribute("pds", pds);
		}
		return "home";
	}
	
	/**
	 * @Name:add
	 * @Description:dispatcher request
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-6 AM9:30:48
	 * @Parameters:
	 * @Return:/WEB-INF/page/workflow/processDefinitionAdd.jsp
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * @Name:save
	 * @Description:save a ProcessDefinition deployment
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-6 AM9:46:16
	 * @Parameters:
	 * @Return:/close.jsp
	 */
	public String save(){
		pdService.deployPD(model);
		return "save";
	}
	
	/**
	 * @Name:delete
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-5 PM9:01:39
	 * @Parameters:
	 * @Return:String
	 */
	public String delete(){
		pdService.deletePDById4AllVersion(model.getId());
		return "delete";
	}
	
	/**
	 * @Name:downloadProcessImage
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-6 PM1:03:47
	 * @Parameters:
	 * @Return:String
	 */
	public String downloadProcessImage(){
		model.setInputStream(pdService.getImageResourceById4New(model.getId()));
		return "downloadProcessImage";
	}
}
