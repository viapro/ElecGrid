package com.viapro.elec.dao;

import com.viapro.elec.bean.ElecSystemDDL;

public interface ElecSystemDDLDao extends BaseDao<ElecSystemDDL> {
    static final String BEAN_NAME = "com.viapro.elec.dao.impl.ElecSystemDDLDaoImpl";
    /**  
     * 
     * @Name: getValueByID（方法的名称）
     * @Description:通过 字典类别 和 类别下id 获得 字典值
     * @Author: lyd（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2013-4-20 15:46:12（创建日期）
     * @Parameters: 无
     * @Return: 对应字典值
     */
    String getValueByID(String keyword, String ddlCode);
    /**  
     * 
     * @Name: getValueByID（方法的名称）
     * @Description:通过 字典类别 和 类别下值  获得 字典名称
     * @Author: lyd（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2013-4-20 15:46:12（创建日期）
     * @Parameters: 1，类别 2，类别下的code
     * @Return: 对应字典名称
     * 
     * getDDLNameByKeyWordAndDDCode("性别","男")   ===》  1
     */
    Integer getDDLNameByKeyWordAndDDCode(String string, String name);
}
