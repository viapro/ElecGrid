package com.viapro.elec.service;

import java.util.List;

import com.viapro.elec.bean.ElecApplication;

public interface ElecApplicationService {
	public static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecApplicationServiceImpl";

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-8 PM3:14:35
	 * @Parameters:
	 * @Return:void
	 */
	void prepareAndStartPD(ElecApplication model);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-8 下午6:39:52
	 * @Parameters:
	 * @Return:List<ElecApplication>
	 */
	List<ElecApplication> findAppsByCondition(ElecApplication model);

	/**
	 * @Name:findMyTasks
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-9 PM2:49:48
	 * @Parameters:
	 * @Return:List<ElecApplication>
	 */
	List<ElecApplication> findMyTasks();

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-9 下午4:32:45
	 * @Parameters:
	 * @Return:Boolean
	 */
	boolean approveTask(ElecApplication model);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-9 下午6:09:36
	 * @Parameters:
	 * @Return:boolean
	 */
	boolean getApplicationResource(ElecApplication model);

}
