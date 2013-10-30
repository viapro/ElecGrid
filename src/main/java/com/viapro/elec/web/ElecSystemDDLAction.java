package com.viapro.elec.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.viapro.elec.bean.ElecSystemDDL;
import com.viapro.elec.service.ElecSystemDDLService;

@Controller("elecSystemDDLAction")
@Scope("prototype")
public class ElecSystemDDLAction extends BaseAction<ElecSystemDDL> {

	private static final long serialVersionUID = 1L;
	
	private ElecSystemDDLService elecSystemDDLService;

    @Resource(name=ElecSystemDDLService.BEAN_NAME)
    public void setElecSystemDDLService(ElecSystemDDLService elecSystemDDLService) {
		this.elecSystemDDLService = elecSystemDDLService;
	}

	/**
	 * @Name:home
	 * @Description:jump to the page of Directory Index page
	 * @Author:N2048
	 * @Version:V1.00
	 * @Create Date:2013-9-22 PM7:17:09
	 * @Parameters:
	 * @Return:/WEB-INF/page/system/dictionaryIndex.jsp
	 */
	public	String home(){
		List<ElecSystemDDL> typeList = elecSystemDDLService.getAlltype();
		request.setAttribute("typeList", typeList);
		return "home";
	}
	
	/**
	 * @Name:
	 * @Description:jump to the page of Directory edit page.
	 * @Author:N2048
	 * @Version:V1.00
	 * @Create Date:2013-9-22 PM 7:01:35
	 * @Parameters:
	 * @Return:/WEB-INF/page/system/dictionaryEdit.jsp
	 */
	public String edit(){
		//如果 页面传过来的 keyword 不是 jerrynew 说明 需要回显
		if (!"jerrynew".equals(model.getKeyword())) {
			List<ElecSystemDDL> list = elecSystemDDLService.getListByKeyword(model);
			request.setAttribute("list", list);
		}
		return "edit";
	}
	
	public	String save(){
		// 从页面  拿到的 参数有什么 1.itemname[]==> 2.keywordname==> 3typeflag==>
		if(!"new".equals(model.getTypeflag()) ){//说明是需要新增一个类型
			//修改
			elecSystemDDLService.deleteByKeyword(model);
		}
		elecSystemDDLService.saveDDlByArray(model);
		return "save";
	}
}
