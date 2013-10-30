package com.viapro.elec.bean;


@SuppressWarnings("serial")
public class ElecRolePopedom implements java.io.Serializable {
	
	private String roleID;      //角色ID
	private String popedomcode; //配置web文件中权限的编码code的字符串连接
	private String remark;      //备注
	
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getPopedomcode() {
		return popedomcode;
	}
	public void setPopedomcode(String popedomcode) {
		this.popedomcode = popedomcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
