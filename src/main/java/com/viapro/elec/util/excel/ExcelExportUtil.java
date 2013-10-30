package com.viapro.elec.util.excel;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import com.viapro.elec.bean.ElecExportFields;
import com.viapro.elec.dao.BaseDao;
import com.viapro.elec.service.ElecExportFieldsService;
import com.viapro.elec.service.ElecSystemDDLService;
import com.viapro.elec.util.HibernateSessionFactory;
import com.viapro.elec.util.QueryBuilder;

import edu.emory.mathcs.backport.java.util.Arrays;

public class ExcelExportUtil<T extends Serializable> implements ExcelExportBase<T> {

	private Class<T> clazz;
	private ArrayList<String> fieldName = null; // excel标题数据集,即导出的中文字段
	private ArrayList<ArrayList<String>> fieldData = null; // excel数据内容
	private ElecExportFields exportFields = null;
	private List<String> expNameList = null;
	// private List<String> expFieldName = null;// 导出的属性字段名
	private List<Integer> containedDDLCodes = null;
	private BaseDao<T> dao;
	private Map<String, Map<Integer, String>> ddlMaps = null;

	@Resource(name = ElecSystemDDLService.BEAN_NAME)
	private ElecSystemDDLService ddlService;

	@Resource(name = ElecExportFieldsService.BEAN_NAME)
	protected ElecExportFieldsService exportFieldsService;

	private String belongTo;

	@SuppressWarnings("unchecked")
	public ExcelExportUtil(String belongTo) {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.belongTo = belongTo;
	}

	public ExcelExportUtil<T> init() {
		if (StringUtils.isNotEmpty(belongTo)) {
			exportFields = exportFieldsService.getElecExportFieldsByBelongToAndCurrentUserID(belongTo);
			expNameList = Arrays.asList(exportFields.getExpNameList().split("#"));
			ddlMaps = ddlService.getAllDDLMaps();
			getContainedDDLCodes();
		}
		return this;
	}

	// @SuppressWarnings("unchecked")
	public ArrayList<String> getFieldName() {
		fieldName = new ArrayList<String>();
		Collections.copy(fieldName, expNameList);
		return fieldName;
	}

	public ArrayList<ArrayList<String>> getFieldData(List<String> whereConditions, List<String> params) {
		if (exportFields != null) {
			fieldData = new ArrayList<ArrayList<String>>();
			// select ***,*** from Elec this where
			QueryBuilder qb = new QueryBuilder(clazz);
			qb.addSelectProperty(exportFields.getExpNameList().replaceAll("#", ","));
			if (whereConditions != null && params != null && whereConditions.size() > 0 && params.size() > 0)
				for (int i = 0; i < params.size(); i++)
					qb.addWhereCondition(whereConditions.get(i), params.get(i));
			@SuppressWarnings("rawtypes")
			List result = qb.queryList(HibernateSessionFactory.getSession(), 1, Integer.MAX_VALUE);

			if (result != null && result.size() > 0) {
				for (Object object : result) {
					// 判断用户导出是否是只导出一个字段，如果只导出一个字段，那么o是单个对象，反之，是object数组
					Object[] objs = object.getClass().isArray() ? (Object[]) object : new Object[] { object };

					ArrayList<String> childList = new ArrayList<String>();
					if (objs != null && objs.length > 0) {
						for (int i = 0; i < objs.length; i++) {
							// 把导出字段中的属性根据数据字典进行自动转换
							if (containedDDLCodes.size() > 0 && containedDDLCodes.contains(i))
								childList.add(objs[i] == null ? "" : ddlMaps.get(expNameList.get(i)).get(objs[i]));
							childList.add(objs[i] == null ? "" : objs[i].toString());
						}
						fieldData.add(childList);
					}
				}
			}
		}
		return fieldData;
	}

	private List<Integer> getContainedDDLCodes() {
		containedDDLCodes = new ArrayList<Integer>();
		for (int i = 0; i < expNameList.size(); i++)
			if (ddlMaps.containsKey(expNameList.get(i)))
				containedDDLCodes.add(i);
		return containedDDLCodes;
	}

}
