package com.viapro.elec.bean;

import java.io.File;
import java.util.Date;

@SuppressWarnings("serial")
public class ElecUser implements java.io.Serializable {
	
	private String userID;       	//主键ID
	private String jctID;      		//所属单位code
	private String userName;      	//用户姓名
	private String logonName;    	//登录名
	private String logonPwd;		//密码
	private String sexID;			//性别
	private Date birthday;			//出生日期
	private String address;			//联系地址
	private String contactTel;		//联系电话 
	private String email;			//电子邮箱
	private String mobile;			//手机
	private String isDuty;			//是否在职
	private Date onDutyDate;    	//入职时间
	private Date offDutyDate;		//离职时间
	private String remark;			//备注
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getJctID() {
		return jctID;
	}
	public void setJctID(String jctID) {
		this.jctID = jctID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogonName() {
		return logonName;
	}
	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}
	public String getLogonPwd() {
		return logonPwd;
	}
	public void setLogonPwd(String logonPwd) {
		this.logonPwd = logonPwd;
	}
	public String getSexID() {
		return sexID;
	}
	public void setSexID(String sexID) {
		this.sexID = sexID;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIsDuty() {
		return isDuty;
	}
	public void setIsDuty(String isDuty) {
		this.isDuty = isDuty;
	}
	public Date getOnDutyDate() {
		return onDutyDate;
	}
	public void setOnDutyDate(Date onDutyDate) {
		this.onDutyDate = onDutyDate;
	}
	public Date getOffDutyDate() {
		return offDutyDate;
	}
	public void setOffDutyDate(Date offDutyDate) {
		this.offDutyDate = offDutyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/*******************非持久化javabean属性***********************************/
	/**
	 * 用来判断是跳转到编辑用户页面还是查看用户页面
	 *    如果viewflag的值为1，表示跳转的是查看用户页面
	 *    如果viewflag的值为null，表示跳转的是编辑用户页面
	 */
	private String viewflag;
	
	/**
	 * 用来判断是否需要对密码进行MD5的加密
	 *    如果md5flag==1：表示此时不需要对密码进行加密，针对系统编译用户并保存的时候，没有修改密码
	 *    如果md5flag==null：表示此时需要对密码进行加密，针对系统新增保存的时候，还有编辑用户并保存的时候，修改了密码
	 */
	private String md5flag;
	
	/**
	 * 用来判断角色关联用户，用户的复选框是否被选中
	 *  * 如果flag=1：表示当前角色拥有该用户，此时用户的复选框要被选中
	 *  * 如果flag=2：表示当前角色不拥有该用户，此时用户的复选框不能被选中
	 */
	private String flag;
	
	/**
	 * 用来判断
	 *   如果是系统管理员，此时roleflag=null，表示点击左侧用户管理的时候，跳转的是userIndex.jsp，并可以在userIndex.jsp中进行增、删、改、查所有用户的操作
	 *      * 执行保存的时候，跳转的是close.jsp，表示关闭子页面，刷新父页面
	 *   如果不是系统管理员，此时roleflag=1，表示点击左侧用户管理的时候，跳转的是userEdit.jsp
	 *      * 执行保存的时候，跳转的是userEdit.jsp，可以继续进行编辑
	 */
	private String roleflag;
	
	/**excel文件导入：设置导入的文件*/
	private File file;

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMd5flag() {
		return md5flag;
	}
	public void setMd5flag(String md5flag) {
		this.md5flag = md5flag;
	}
	public String getViewflag() {
		return viewflag;
	}
	public void setViewflag(String viewflag) {
		this.viewflag = viewflag;
	}
	public String getRoleflag() {
		return roleflag;
	}
	public void setRoleflag(String roleflag) {
		this.roleflag = roleflag;
	}
	
	
	/*******************非持久化javabean属性***********************************/
	
	
}
