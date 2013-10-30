package com.viapro.elec.bean;

import java.io.File;
import java.io.InputStream;


@SuppressWarnings("serial")
public class ElecApplicationTemplate implements java.io.Serializable {
	
	private Long id;//主键ID
	private String name;//名称
	private String processDefinitionKey;//流程定义的key
	private String path;//上传的模板文件的存储位置
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	/*********************非持久化javabean属性***************************/
	//用于上传申请模板文件
	private File upload;
	//用户文件下载的输入流
	private InputStream inputStream;
	// 封装上传文件类型的属性
	private String uploadContentType;
	// 封装上传文件名的属性
	private String uploadFileName;

	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
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
	
	
	/*********************非持久化javabean属性***************************/
	
}
