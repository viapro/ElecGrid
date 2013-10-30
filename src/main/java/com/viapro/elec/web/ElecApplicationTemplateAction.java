package com.viapro.elec.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.viapro.elec.bean.ElecApplicationTemplate;
import com.viapro.elec.service.ElecApplicationTemplateService;
import com.viapro.elec.service.ElecProcessDefinitionService;
import com.viapro.elec.util.FileUploadUtil;

@Controller("elecApplicationTemplateAction")
@Scope("prototype")
public class ElecApplicationTemplateAction extends BaseAction<ElecApplicationTemplate> {

	private static final long serialVersionUID = 1L;

	private ElecApplicationTemplateService atService;
	
	private ElecProcessDefinitionService pdService;

	@Resource(name = ElecApplicationTemplateService.BEAN_NAME)
	public void setAtService(ElecApplicationTemplateService atService) {
		this.atService = atService;
	}

	@Resource(name = ElecProcessDefinitionService.BEAN_NAME)
	public void setPdService(ElecProcessDefinitionService pdService) {
		this.pdService = pdService;
	}

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 上午9:25:29
	 * @Parameters:
	 * @Return:String
	 */
	public String home() {
		List<ElecApplicationTemplate> ats = atService.findAll();
		request.setAttribute("ats", ats);
		return "home";
	}
	
	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 上午9:27:11
	 * @Parameters:
	 * @Return:String
	 */
	public String add(){
		List<ProcessDefinition> pds = pdService.getProcessDefinition4New();
		request.setAttribute("pds", pds);
		return "add";
	}
	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 上午10:02:36
	 * @Parameters:
	 * @Return:String
	 */
	public String save(){
		atService.save(model);
		return "save";
	}
	
	/**
	 * @Name:update
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 PM12:28:54
	 * @Parameters:
	 * @Return:String
	 */
	public String update(){
		atService.update(model);
		return "update";
	}
	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 上午9:27:35
	 * @Parameters:
	 * @Return:String
	 */
	public String edit(){
		ServletActionContext.getContext().getValueStack().pop();
		ServletActionContext.getContext().getValueStack().push(atService.getApplicationTemplateById(model.getId()));
		request.setAttribute("pds", pdService.getProcessDefinition4New());
		return "edit";
	}
	
	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 下午2:06:22
	 * @Parameters:
	 * @Return:String
	 */
	public String download(){
		atService.getApplicationTemplateResource(model);
		return "download";
	}
	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 上午9:27:56
	 * @Parameters:
	 * @Return:String
	 */
	public String delete(){
		atService.deleteApplicationTemplate(model);
		return "delete";
	}

}
