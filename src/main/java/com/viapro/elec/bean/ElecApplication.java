package com.viapro.elec.bean;

import java.io.File;
import java.io.InputStream;
import java.util.Date;


@SuppressWarnings("serial")
public class ElecApplication implements java.io.Serializable {
	
	private Long applicationID;			//主键ID
	private Long applicationTemplateID;	//申请模板表的主键
	private String title;				//上传的文件标题
	private String path;				//上传的文件的存储路径
	private String applicationUserID;	//申请人ID
	private String applicationLogonName;//申请人登录名
	private String applicationUserName;	//申请人姓名
	private Date applyTime;				//申请日期
	private String status;				//当前审批状态
	
	public Long getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(Long applicationID) {
		this.applicationID = applicationID;
	}
	public Long getApplicationTemplateID() {
		return applicationTemplateID;
	}
	public void setApplicationTemplateID(Long applicationTemplateID) {
		this.applicationTemplateID = applicationTemplateID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getApplicationUserID() {
		return applicationUserID;
	}
	public void setApplicationUserID(String applicationUserID) {
		this.applicationUserID = applicationUserID;
	}
	public String getApplicationLogonName() {
		return applicationLogonName;
	}
	public void setApplicationLogonName(String applicationLogonName) {
		this.applicationLogonName = applicationLogonName;
	}
	public String getApplicationUserName() {
		return applicationUserName;
	}
	public void setApplicationUserName(String applicationUserName) {
		this.applicationUserName = applicationUserName;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/******************************非持久化javabean属性*******************************/
	public static final String APPLICATION_RUNNING = "1";//审核中
	public static final String APPLICATION_REJECT = "2"; //审核不通过
	public static final String APPLICATION_PASS = "3";   //审核通过
	
	//任务ID
	private String taskId;
	
	//连线的名称
	private String outcome;
	
	//审核信息
	private Long approveID;			//主键ID
	private String comment;			//审批意见
	private boolean approval;		//审批结果，是否通过（同意或者不同意）
	private String approveUserID;	//审批人ID
	private String approveUserName;	//审批人姓名
	private Date approveTime;		//审批日期
	
	//完成申请文件的上传
	private File upload;
	// 封装上传文件类型的属性
	private String uploadContentType;
	// 封装上传文件名的属性
	private String uploadFileName;
	
	//文件下载
	private InputStream inputStream;

	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public Long getApproveID() {
		return approveID;
	}
	public void setApproveID(Long approveID) {
		this.approveID = approveID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}
	public String getApproveUserID() {
		return approveUserID;
	}
	public void setApproveUserID(String approveUserID) {
		this.approveUserID = approveUserID;
	}
	public String getApproveUserName() {
		return approveUserName;
	}
	public void setApproveUserName(String approveUserName) {
		this.approveUserName = approveUserName;
	}
	public Date getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	
	/******************************非持久化javabean属性*******************************/
}
