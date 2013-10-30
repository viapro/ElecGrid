package com.viapro.elec.service;

import java.util.List;

import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.bean.ElecUserRole;
import com.viapro.elec.vo.FunctionXml;

public interface ElecRoleService {
	static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecRoleServiceImpl";

	/**
	 * @Name:getAllPopedom
	 * @Description:get the list of FunctionXml
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-25 AM9:16:19
	 * @Parameters:~
	 * @Return:List<FunctionXml>
	 */
	List<FunctionXml> getAllPopedom();

	/**
	 * @Name:getAllPopedom4flag
	 * @Description:get the list of FunctionXml whit the checked-flag property
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-27 PM6:59:17
	 * @Parameters:ElecUserRole
	 * @Return:List<FunctionXml>
	 */
	List<FunctionXml> getAllPopedom4flag(ElecUserRole elecUserRole);

	/**
	 * @Name:getAllUser4flag
	 * @Description:get the list of FunctionXml whit the checked-flag property
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 AM8:53:00
	 * @Parameters:ElecUserRole
	 * @Return:List<ElecUser>
	 */
	List<ElecUser> getAllUser4flag(ElecUserRole model);

	/**
	 * @Name:saveUserRoleResource
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 上午10:25:31
	 * @Parameters:
	 * @Return:void
	 */
	void saveUserRoleResource(ElecUserRole model);
}
