package com.viapro.elec.bean;


@SuppressWarnings("serial")
public class ElecSystemDDL implements java.io.Serializable {
	
	private Integer seqID;       //主键ID(自增长)
	private String keyword;      //数据类型
	private Integer ddlCode;     //数据项的code
	private String ddlName;      //数据项的value
	
	public Integer getSeqID() {
		return seqID;
	}
	public void setSeqID(Integer seqID) {
		this.seqID = seqID;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(Integer ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	
	/*****************************非持久化javabean属性**************************************/
	//存放数据类型
	private String keywordname;
	
	//存放表单中传递的数据项的值
	private String [] itemname;
	
	/**
	 * 用来做业务判断的标识:
	 *     new：新增一种数据类型
     *     add：在已有数据类型的基础上进行修改和编辑
	 */
	private String typeflag;

	
	public String getKeywordname() {
		return keywordname;
	}
	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}
	public String[] getItemname() {
		return itemname;
	}
	public void setItemname(String[] itemname) {
		this.itemname = itemname;
	}
	public String getTypeflag() {
		return typeflag;
	}
	public void setTypeflag(String typeflag) {
		this.typeflag = typeflag;
	}
	
	
	
	/*****************************非持久化javabean属性**************************************/
	
}
