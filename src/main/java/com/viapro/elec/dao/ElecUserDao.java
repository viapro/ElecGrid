package com.viapro.elec.dao;

import java.util.List;

import com.viapro.elec.bean.ElecUser;

public interface ElecUserDao extends BaseDao<ElecUser> {
    static final String BEAN_NAME = "com.viapro.elec.dao.impl.ElecUserDaoImpl";

	/**
	 * @Name:getUsersByUserName
	 * @Description:Query users by userName
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-23 PM9:21:48
	 * @Parameters:userName
	 * @Return:List<ElecUser>
	 */
	List<ElecUser> getUsersByUserName(String userName);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 下午2:26:27
	 * @Parameters:
	 * @Return:ElecUser
	 */
	ElecUser getUserByLogonName(String logonName);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 下午3:43:49
	 * @Parameters:
	 * @Return:List<Object[]>
	 */
	List<Object[]> getRoleByLogonName(String logonName);
}
