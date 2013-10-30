package com.viapro.elec.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viapro.elec.bean.ElecCommonMsg;
import com.viapro.elec.dao.ElecCommonMsgDao;
import com.viapro.elec.service.ElecCommonMsgService;

@Service(ElecCommonMsgService.BEAN_NAME)
@Transactional
public class ElecCommonMsgServiceImpl implements ElecCommonMsgService {

	private ElecCommonMsgDao elecCommonMsgDao;

	@Resource(name = ElecCommonMsgDao.BEAN_NAME)
	public void setElecCommonMsgDao(ElecCommonMsgDao elecCommonMsgDao) {
		this.elecCommonMsgDao = elecCommonMsgDao;
	}

	@Override
    public void save(ElecCommonMsg model) {
		//查询数据库中 有没有已存在记录
		ElecCommonMsg msg = this.getCommonMsg();
		if(msg==null){
			elecCommonMsgDao.save(model);
		}else{
			msg.setDevRun(model.getDevRun());
			msg.setStationRun(model.getStationRun());
			msg.setCreateDate(new Date());
			elecCommonMsgDao.update(msg);
		}
	}
	
	@Override
	public ElecCommonMsg getCommonMsg() {
		List<ElecCommonMsg> list = elecCommonMsgDao.getAll();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
