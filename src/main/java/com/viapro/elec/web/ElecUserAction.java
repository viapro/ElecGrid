package com.viapro.elec.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.service.ElecSystemDDLService;
import com.viapro.elec.service.ElecUserService;
import com.viapro.elec.util.ExcelFileGenerator;
import com.viapro.elec.util.MD5keyBean;
import com.viapro.elec.util.excel.ExcelExportUtil;
import com.viapro.elec.util.excel.UserExcelUtil;

@Controller("elecUserAction")
@Scope("prototype")
public class ElecUserAction extends BaseAction<ElecUser> {

	private static final long serialVersionUID = 1L;

	private ElecUserService userService;
	private ElecSystemDDLService ddlService;

	@Resource(name = ElecSystemDDLService.BEAN_NAME)
	public void setDdlService(ElecSystemDDLService ddlService) {
		this.ddlService = ddlService;
	}

	@Resource(name = ElecUserService.BEAN_NAME)
	public void setElecUserService(ElecUserService elecUserService) {
		this.userService = elecUserService;
	}

	/**
	 * @Name:home
	 * @Description:goto the user index page
	 * @Author:N2048
	 * @Version:V1.00
	 * @Create Date:2013-9-23 PM2:03:24
	 * @Parameters:~
	 * @Return:/WEB-INF/page/system/userIndex.jsp
	 */
	public String home() {
		List<ElecUser> elecUsers = userService.getUsersByUserName(model);
		request.setAttribute("elecUsers", elecUsers);
		return "home";
	}

	/**
	 * @Name:add
	 * @Description:goto the userAdd page
	 * @Author:N2048
	 * @Version:V1.00
	 * @Create Date:2013-9-23 PM2:11:29
	 * @Parameters:~
	 * @Return:/WEB-INF/page/system/userAdd.jsp
	 */
	public String add() {
		initSystemDDL();
		return "add";
	}

	/**
	 * @Name:initSystemDDL
	 * @Description:prepare the SystemDDL data
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-24 PM12:46:07
	 * @Parameters:~
	 * @Return:void
	 */
	private void initSystemDDL() {
		request.setAttribute("sexList", ddlService.getListByKeyword("性别"));
		request.setAttribute("jctList", ddlService.getListByKeyword("所属单位"));
		request.setAttribute("isDutyList", ddlService.getListByKeyword("是否在职"));
	}

	/**
	 * @Name:save
	 * @Description:save the user to be added
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-24 AM10:59:22
	 * @Parameters:~
	 * @Return:/WEB-INF/close.jsp
	 */
	public String save() {
		MD5keyBean md5 = new MD5keyBean();
		if (StringUtils.isEmpty(model.getLogonPwd()))
			model.setLogonPwd("111111");
		if (StringUtils.isEmpty(model.getMd5flag()))
			model.setLogonPwd(md5.getkeyBeanofStr(model.getLogonPwd()));
		userService.saveUser(model);
		return "save";
	}

	/**
	 * @Name:edit
	 * @Description:goto the page of editing user's information
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-24 PM12:39:45
	 * @Parameters:~
	 * @Return:/WEB-INF/page/system/userEdit.jsp
	 */
	public String edit() {
		// 根据userID来获取user, 并将user压到栈顶便于编辑页面的回显
		ServletActionContext.getContext().getValueStack().pop();
		ServletActionContext.getContext().getValueStack().push(userService.getUserById(model.getUserID()));
		initSystemDDL();
		return "edit";
	}

	/**
	 * @Name:delete
	 * @Description:delete user
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-24 PM2:06:14
	 * @Parameters:~
	 * @Return:home
	 */
	public String delete() {
		userService.deleteUserById(model.getUserID());
		return "delete";
	}

	/**
	 * @Name:exportExcel
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-10 下午10:45:54
	 * @Parameters:
	 * @Return:String
	 */
	public String exportExcel() {
		List<String> whereConditions = new ArrayList<String>();
		List<String> params = new ArrayList<String>();
		whereConditions.add("this.userName=?");
		params.add(model.getUserName());
		ExcelExportUtil userExcelUtil = new UserExcelUtil("5-1").init();
//		excelService = new ExcelExportServiceImpl<ElecUser>("5-1");
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(userExcelUtil.getFieldName(), userExcelUtil.getFieldData(whereConditions, params));
		try {
			// 获取输出流对象
			OutputStream os = response.getOutputStream();
			// 重置输出流的缓冲区，reset()方法可以不写，但是在输出之前要保重response缓冲区中没有任何内容，否则不能输出结果
			response.reset();
			// 定义输出的格式是excel的格式
			response.setContentType("application/vnd.ms-excel");
			// 定义excel报表的文件名
			String filename = "用户报表.xls";
			// 处理文件中文乱码问题
			filename = URLEncoder.encode(filename, "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
			excelFileGenerator.expordExcel(os);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return null;
	}
}
