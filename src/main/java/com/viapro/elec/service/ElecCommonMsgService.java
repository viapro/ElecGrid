package com.viapro.elec.service;

import com.viapro.elec.bean.ElecCommonMsg;

/**
 * Created with IntelliJ IDEA.
 * User: N2048
 * Date: 13-8-22
 * Time: 下午1:35
 * To change this template use File | Settings | File Templates.
 */
public interface ElecCommonMsgService {
    static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecCommonMsgServiceImpl";
    void save(ElecCommonMsg model);
	ElecCommonMsg getCommonMsg();
}
