package com.viapro.elec.dao;

import java.util.List;

import com.viapro.elec.bean.ElecUserRole;

public interface ElecUserRoleDao extends BaseDao<ElecUserRole> {
	public static final String BEAN_NAME="com.viapro.elec.dao.impl.ElecUserRoleDaoImpl";

	/**
	 * @Name:getUser4flag
	 * @Description:get users with flag property by roleID
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 上午8:58:15
	 * @Parameters:String
	 * @Return:List<Object[]>
	 */
	List<Object[]> getUser4flag(String roleID);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 上午11:04:32
	 * @Parameters:
	 * @Return:void
	 */
	void deleteUserRoleByRoleId(String roleID);
}
