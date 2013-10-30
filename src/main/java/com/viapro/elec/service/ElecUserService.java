package com.viapro.elec.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.viapro.elec.bean.ElecUser;

public interface ElecUserService {
	static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecUserServiceImpl";

	/**
	 * @Name:getUsersByUserName
	 * @Description:Query users by userName
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-23 PM9:07:48
	 * @Parameters:~
	 * @Return:List<ElecUser>
	 */
	List<ElecUser> getUsersByUserName(ElecUser model);

	/**
	 * @Name:saveUser
	 * @Description:save a new user
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-24 AM11:58:04
	 * @Parameters:user
	 * @Return:void
	 */
	void saveUser(ElecUser user);

	/**
	 * @Name:getUserById
	 * @Description:Query user by userID
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-24 PM12:50:37
	 * @Parameters:userID
	 * @Return:ElecUser
	 */
	ElecUser getUserById(String userID);

	/**
	 * @Name:deleteUserById
	 * @Description:delete user by userID
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-24 PM2:26:03
	 * @Parameters:userID
	 * @Return:void
	 */
	void deleteUserById(String userID);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 下午2:24:51
	 * @Parameters:
	 * @Return:ElecUser
	 */
	ElecUser getUserByLogonName(String name);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 下午3:38:58
	 * @Parameters:
	 * @Return:Map<String,String>
	 */
	Map<String, String> getRoleMapByLogonName(String name);

	/**
	 * @Name:getResourceByRoleIds
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 PM4:19:43
	 * @Parameters:~
	 * @Return:String
	 */
	String getResourceByRoleIds(Set<String> roleIds);

}
