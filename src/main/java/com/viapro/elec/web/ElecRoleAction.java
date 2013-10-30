package com.viapro.elec.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.viapro.elec.bean.ElecRolePopedom;
import com.viapro.elec.bean.ElecSystemDDL;
import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.bean.ElecUserRole;
import com.viapro.elec.service.ElecRoleService;
import com.viapro.elec.service.ElecSystemDDLService;
import com.viapro.elec.vo.FunctionXml;

@Controller("elecRoleAction")
@Scope("prototype")
public class ElecRoleAction extends BaseAction<ElecUserRole> {

	private ElecRoleService roleService;
	private ElecSystemDDLService ddlService;

	@Resource(name=ElecRoleService.BEAN_NAME)
	public void setRoleService(ElecRoleService roleService) {
		this.roleService = roleService;
	}

	@Resource(name=ElecSystemDDLService.BEAN_NAME)
	public void setDdlService(ElecSystemDDLService ddlService) {
		this.ddlService = ddlService;
	}
	
	/**
	 * @Name:home
	 * @Description:go to the page of roleIndex.jsp
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 AM10:22:17
	 * @Parameters:~
	 * @Return:String
	 */
	public String home(){
		//1.准备角色类型下拉列表
		List<ElecSystemDDL> roleList = ddlService.getListByKeyword("角色类型");//########又是一个不合理的地方
		request.setAttribute("roleList", roleList);
		//2.获取所有functionXML列表
		List<FunctionXml> fXmls =  roleService.getAllPopedom();
		request.setAttribute("fXmls", fXmls);
		return "home";
	}
	
	/**
	 * @Name:edit
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 AM10:23:03
	 * @Parameters:
	 * @Return:String
	 */
	public String edit(){
		List<FunctionXml> fXmls =  roleService.getAllPopedom4flag(model);
		request.setAttribute("fXmls", fXmls);
		List<ElecUser> users = roleService.getAllUser4flag(model);
		request.setAttribute("users", users);
		return "edit";
	}
	
	/**
	 * @Name:save
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 AM10:23:36
	 * @Parameters:
	 * @Return:String
	 */
	public String save(){
		roleService.saveUserRoleResource(model);
		return "save";
	}
	

}
