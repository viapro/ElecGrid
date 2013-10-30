package com.viapro.elec.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viapro.elec.bean.ElecApplication;
import com.viapro.elec.bean.ElecApproveInfo;
import com.viapro.elec.dao.ElecApproveInfoDao;
import com.viapro.elec.service.ElecApproveInfoService;
import com.viapro.elec.util.QueryBuilder;

@Service(ElecApproveInfoService.BEAN_NAME)
@Transactional(readOnly = true)
public class ElecApproveInfoServiceImpl implements ElecApproveInfoService {
	
	private ElecApproveInfoDao aiDao;

	@Resource(name=ElecApproveInfoDao.BEAN_NAME)
	public void setAiDao(ElecApproveInfoDao aiDao) {
		this.aiDao = aiDao;
	}

	@Override
	public List<ElecApproveInfo> findApprovedHistory(ElecApplication model) {
		QueryBuilder qb = new QueryBuilder(ElecApproveInfo.class);
		qb.addWhereCondition("this.applicationID=?", model.getApplicationID()).addOrderProperty("this.approveTime", false);
		return aiDao.getList(qb, 1);
	}

}
