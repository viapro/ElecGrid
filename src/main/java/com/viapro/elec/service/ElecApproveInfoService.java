package com.viapro.elec.service;

import java.util.List;

import com.viapro.elec.bean.ElecApplication;
import com.viapro.elec.bean.ElecApproveInfo;

public interface ElecApproveInfoService {
	public static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecApproveInfoServiceImpl";

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-9 PM9:19:32
	 * @Parameters:
	 * @Return:List<ElecApproveInfo>
	 */
	List<ElecApproveInfo> findApprovedHistory(ElecApplication model);
}
