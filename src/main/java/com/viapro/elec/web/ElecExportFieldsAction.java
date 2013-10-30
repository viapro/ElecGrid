package com.viapro.elec.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.viapro.elec.bean.ElecExportFields;
import com.viapro.elec.service.ElecExportFieldsService;
import com.viapro.elec.util.StringTool;

@Controller("elecExportFieldsAction")
@Scope("prototype")
public class ElecExportFieldsAction extends BaseAction<ElecExportFields> {

	private static final long serialVersionUID = 1L;

	private ElecExportFieldsService elecExportFieldsService;

	@Resource(name = ElecExportFieldsService.BEAN_NAME)
	public void setElecExportFieldsService(ElecExportFieldsService elecExportFieldsService) {
		this.elecExportFieldsService = elecExportFieldsService;
	}

	/**
	 * @Name:setExportExcel
	 * @Description:go to exportExcel.jsp with the user's preferences of export columns
	 * @Author:N2048
	 * @Version:V1.00
	 * @Create Date:2013-9-22 下午6:22:39
	 * @Parameters:
	 * @Return:String
	 */
	public String setExportExcel() {

		ElecExportFields elecExportFields = elecExportFieldsService.getElecExportFields(model);

		ServletActionContext.getContext().getValueStack().pop();
		ServletActionContext.getContext().getValueStack().push(elecExportFields);

		// 对字符串根据#来拆分
		String[] ExpName = StringTool.getArray(elecExportFields.getExpNameList(), "#");
		String[] ExpField = StringTool.getArray(elecExportFields.getExpFieldName(), "#");
		String[] noExpName = StringTool.getArray(elecExportFields.getNoExpNameList(), "#");
		String[] noExpField = StringTool.getArray(elecExportFields.getNoExpFieldName(), "#");

		// 未被选中的列表
		Map<String, String> nomap = new LinkedHashMap<String, String>();
		if (noExpName != null && noExpName.length > 0) {
			for (int i = 0; i < noExpName.length; i++) {
				nomap.put(noExpField[i], noExpName[i]);
			}
		}

		// 被选中的列表
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < ExpName.length; i++) {
			map.put(ExpField[i], ExpName[i]);
		}

		request.setAttribute("map", map);
		request.setAttribute("nomap", nomap);

		return "setExportExcel";
	}

	/**
	 * @Name:saveSetExportExcel
	 * @Description:save the list of export set
	 * @Author:N2048
	 * @Version:V1.00
	 * @Create Date:2013-9-22 PM 6:13:27
	 * @Parameters:
	 * @Return:String
	 */
	public String saveSetExportExcel() {
		elecExportFieldsService.save(model);
		return "saveSetExportExcel";
	}

}
