package com.viapro.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.viapro.elec.bean.ElecRolePopedom;
import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.bean.ElecUserRole;
import com.viapro.elec.dao.ElecRolePopedomDao;
import com.viapro.elec.dao.ElecUserRoleDao;
import com.viapro.elec.service.ElecRoleService;
import com.viapro.elec.vo.FunctionXml;

@Service(ElecRoleService.BEAN_NAME)
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class ElecRoleServiceImpl implements ElecRoleService {

	private ElecRolePopedomDao popedomDao;

	@Resource(name = ElecRolePopedomDao.BEAN_NAME)
	public void setPopedomDao(ElecRolePopedomDao popedomDao) {
		this.popedomDao = popedomDao;
	}

	private ElecUserRoleDao roleDao;

	@Resource(name = ElecUserRoleDao.BEAN_NAME)
	public void setRoleDao(ElecUserRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viapro.elec.service.ElecRoleService#getAllPopedom()
	 */
	// 这个方法与类似于数据库的xml文件打交道，放在这是否适合？
	/*
	 * <ElecData> <Function> <FunctionCode>aa</FunctionCode> <FunctionName>仪器设备管理</FunctionName> <ParentCode>device</ParentCode> <ParentName>技术设施维护管理</ParentName> <url>/aa</url> </Function> </ElecData>
	 */

	@Override
	public List<FunctionXml> getAllPopedom() {
		SAXReader saxReader = new SAXReader();
		List<FunctionXml> fXmls = null;
		try {
			Document doc = saxReader.read(new File(ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/Function.xml")));
			Element root = doc.getRootElement(); // <ElecData>
			Iterator<Element> it = root.elementIterator();
			fXmls = new ArrayList<FunctionXml>();
			while (it.hasNext()) {
				Element e = it.next();// e is Function Node
				FunctionXml fXml = new FunctionXml();
				fXml.setCode(e.elementText("FunctionCode"));
				fXml.setName(e.elementText("FunctionName"));
				fXml.setParentCode(e.elementText("ParentCode"));
				fXml.setParentName(e.elementText("ParentName"));
				fXml.setUrl(e.elementText("url"));
				fXmls.add(fXml);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return fXmls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viapro.elec.service.ElecRoleService#getAllPopedom4flag(com.viapro.elec.bean.ElecUserRole)
	 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public List<FunctionXml> getAllPopedom4flag(ElecUserRole elecUserRole) {
		List<FunctionXml> fXmls = getAllPopedom();
		ElecRolePopedom popedom = popedomDao.getById(elecUserRole.getRoleID());
		if(popedom == null){
			popedom = new ElecRolePopedom();
			popedom.setRoleID(elecUserRole.getRoleID());
			popedom.setPopedomcode("");
			popedomDao.save(popedom);
		}
		String popedomCode = popedom.getPopedomcode();
		for (FunctionXml fXml : fXmls) {
			if (popedomCode.contains(fXml.getCode()))
				fXml.setFlag("1");
			else
				fXml.setFlag("2");
		}
		return fXmls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viapro.elec.service.ElecRoleService#getAllUser4flag(com.viapro.elec.bean.ElecUserRole)
	 */
	@Override
	public List<ElecUser> getAllUser4flag(ElecUserRole model) {
		List<Object[]> result = roleDao.getUser4flag(model.getRoleID());
		List<ElecUser> users = null;
		if (result != null && result.size() > 0) {
			users = new ArrayList<ElecUser>();
			for (Object[] objs : result) {
				ElecUser user = new ElecUser();
				user.setUserID(objs[0].toString() + "");
				user.setUserName(objs[1].toString() + "");
				user.setLogonName(objs[2].toString() + "");
				user.setFlag(objs[3].toString() + "");
				users.add(user);
			}
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viapro.elec.service.ElecRoleService#saveUserRoleResource(com.viapro.elec.bean.ElecUserRole)
	 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveUserRoleResource(ElecUserRole model) {
		this.saveRoleResource(model.getRoleID(), model.getSelectoper());
		this.saveRoleUser(model.getRoleID(), model.getSelectuser());
	}

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 上午10:28:43
	 * @Parameters:
	 * @Return:void
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	private void saveRoleUser(String roleID, String[] selectuser) {
		roleDao.deleteUserRoleByRoleId(roleID);
		if (!StringUtils.isEmpty(roleID) && selectuser != null && selectuser.length > 0)
			for (String userID : selectuser) {
				ElecUserRole role = new ElecUserRole();
				role.setRoleID(roleID);
				role.setUserID(userID);
				roleDao.save(role);
			}
	}

	/**
	 * @Name:saveRoleResource
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 上午10:28:35
	 * @Parameters:String roleID, String[] selectoper
	 * @Return:void
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	private void saveRoleResource(String roleID, String[] selectoper) {
		popedomDao.delete(roleID);
		if (!StringUtils.isEmpty(roleID) && selectoper != null && selectoper.length > 0){
			ElecRolePopedom popedom = new ElecRolePopedom();
			popedom.setRoleID(roleID);
			StringBuffer sb = new StringBuffer();
			for(String functionCode : selectoper)
				sb.append(functionCode).append("@");
			popedom.setPopedomcode(sb.substring(0, sb.lastIndexOf("@")).toString());
			popedomDao.save(popedom);
		}
	}

}
