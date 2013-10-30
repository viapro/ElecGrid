package com.viapro.elec.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.viapro.elec.bean.ElecRolePopedom;
import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.dao.ElecRolePopedomDao;
import com.viapro.elec.dao.ElecSystemDDLDao;
import com.viapro.elec.dao.ElecUserDao;
import com.viapro.elec.service.ElecUserService;

@Service(ElecUserService.BEAN_NAME)
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public class ElecUserServiceImpl implements ElecUserService {

	private ElecUserDao userDao;
	
	private ElecSystemDDLDao ddlDao;

	private ElecRolePopedomDao popedomDao;
	
	@Resource(name = ElecUserDao.BEAN_NAME)
	public void setUserDao(ElecUserDao userDao) {
		this.userDao = userDao;
	}

	@Resource(name = ElecRolePopedomDao.BEAN_NAME)
	public void setPopedomDao(ElecRolePopedomDao popedomDao) {
		this.popedomDao = popedomDao;
	}

	@Resource(name = ElecSystemDDLDao.BEAN_NAME)
	public void setDdlDao(ElecSystemDDLDao ddlDao) {
		this.ddlDao = ddlDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.viapro.elec.service.ElecUserService#getUsersByUserName(com.viapro.elec.bean.ElecUser)
	 */
	@Override
	public List<ElecUser> getUsersByUserName(ElecUser model) {
		List<ElecUser> users;
		if (StringUtils.isEmpty(model.getUserName())) users = userDao.getAll();
		else users = userDao.getUsersByUserName(model.getUserName());
		for(ElecUser user : users){
			//把“性别”带入代码中觉得极为不适，怎样纠正？从页面中获取？
			user.setSexID(ddlDao.getValueByID("性别", user.getSexID()));
			user.setJctID(ddlDao.getValueByID("所属单位", user.getJctID()));
			user.setIsDuty(ddlDao.getValueByID("是否在职", user.getIsDuty()));
		}
		return users;
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.service.ElecUserService#saveUser(com.viapro.elec.bean.ElecUser)
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED,readOnly=false)
	public void saveUser(ElecUser user) {
		userDao.saveOrUpdate(user);
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.service.ElecUserService#getUserById(java.lang.String)
	 */
	@Override
	public ElecUser getUserById(String userID) {
		return userDao.getById(userID);
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.service.ElecUserService#deleteUserById(java.lang.String)
	 */
	@Override
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUserById(String userID) {
		// TODO Auto-generated method stub
		userDao.delete(userID);
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.service.ElecUserService#getUserByLogonName(java.lang.String)
	 */
	@Override
	public ElecUser getUserByLogonName(String logonName) {
		return userDao.getUserByLogonName(logonName);
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.service.ElecUserService#getRoleMapByLogonName(java.lang.String)
	 */
	@Override
	public Map<String, String> getRoleMapByLogonName(String logonName) {
		List<Object[]> result = userDao.getRoleByLogonName(logonName);
		Map<String, String> userRoleMap = null;
		if(result!=null && result.size()>0){
			userRoleMap = new LinkedHashMap<String, String>();
			for(Object[] objs: result)
				userRoleMap.put(objs[0].toString(), objs[1].toString());
		}
		return userRoleMap;
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.service.ElecUserService#getResourceByRoleIds(java.util.Set)
	 */
	@Override
	public String getResourceByRoleIds(Set<String> roleIds) {
		StringBuffer userPopedom = null;
		if(roleIds!=null && roleIds.size()>0){
			userPopedom = new StringBuffer();
			for(String roleId : roleIds){
				ElecRolePopedom popedom = popedomDao.getById(roleId);
				if (popedom!=null)
					userPopedom.append(popedom.getPopedomcode());
			}
			if(userPopedom!=null && userPopedom.length()>0)
				return userPopedom.substring(0, userPopedom.lastIndexOf("a")).toString();
		}
		return null;
	}

}
