package com.viapro.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.dao.ElecUserDao;
import com.viapro.elec.util.QueryBuilder;

@SuppressWarnings("unchecked")
@Repository(ElecUserDao.BEAN_NAME)
public class ElecUserDaoImpl extends BaseDaoImpl<ElecUser> implements ElecUserDao {

	/* (non-Javadoc)
	 * @see com.viapro.elec.dao.ElecUserDao#getUsersByUserName(java.lang.String)
	 */
	@Override
	public List<ElecUser> getUsersByUserName(String userName) {
		//from ElecUser where userName like ?
		//纪念：模糊查询中把like变成了=号，导致查询无效！知识薄弱点:sql及hql的语法结构理解
		QueryBuilder queryBuilder = new QueryBuilder(ElecUser.class).addWhereCondition("this.userName like ?", "%"+userName+"%");
		return getList(queryBuilder, 1);
//		return getSession().createQuery("from ElecUser where userName=?").setParameter(0, userName).list();
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.dao.ElecUserDao#getUserByLogonName(java.lang.String)
	 */
	@Override
	public ElecUser getUserByLogonName(String logonName) {
		return (ElecUser) getSession().createQuery("FROM ElecUser this WHERE this.logonName = :logonName").setString("logonName", logonName).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.dao.ElecUserDao#getRoleByLogonName()
	 */
	@Override
	public List<Object[]> getRoleByLogonName(String logonName) {
		return getSession().createSQLQuery(
				"SELECT c.ddlCode roleId, c.ddlName roleName from elec_user a " +
				"INNER JOIN elec_user_role b ON a.userID = b.userID " +
				"INNER JOIN elec_systemddl c on b.roleID = c.ddlCode AND c.keyword = ? " +
				"WHERE a.logonName = ? AND a.isDuty = '1'")
				.setString(0, "角色类型")
				.setString(1, logonName)
				.list();
	}
	
}
