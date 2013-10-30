package com.viapro.elec.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.viapro.elec.bean.ElecText;
import com.viapro.elec.service.ElecTextService;

@Controller("elecTextAction")
@Scope("prototype")
public class ElecTextAction extends BaseAction<ElecText> {

	private static final long serialVersionUID = 1L;
	
	private ElecTextService etService;
	
	@Resource(name=ElecTextService.BEAN_NAME)
	public void setEtService(ElecTextService etService){
		this.etService = etService;
	}
	
	public String toAdd(){
		return "toAdd";
	}

	public String add(){
		etService.saveElecText(model);
		return "add";
	}
}
