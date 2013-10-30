package com.viapro.elec.vo;

public class FunctionXml {
	
	/*
	<Function>
     <FunctionCode>ai</FunctionCode>
     <FunctionName>用户管理</FunctionName>
     <ParentCode>sysmng</ParentCode>
     <ParentName>系统管理</ParentName>
     <url>/system/elecUserAction_home.do</url>
	</Function>
	 */
	private String code;		//权限code
	private String name;		//权限名称
	private String parentCode;	//父级权限code
	private String parentName;	//父级权限名称
	private String url;			//权限访问的路径url
	
	/**
	 * 作为判断页面权限的复选框是否被选中的一个标识
	 *   如果flag=1：表明页面的复选框被选中，说明当前角色具有该权限
	 *   如果flag=2：表明页面的复选框不被选中，说明当前角色不具有该权限
	 */
	private String flag;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
