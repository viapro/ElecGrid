package com.viapro.elec.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.viapro.elec.bean.ElecUserRole;
import com.viapro.elec.dao.ElecUserRoleDao;

@Repository(ElecUserRoleDao.BEAN_NAME)
// 把dao纳入到spring容器的管理中
public class ElecUserRoleDaoImpl extends BaseDaoImpl<ElecUserRole> implements ElecUserRoleDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viapro.elec.dao.ElecUserRoleDao#getUser4flag(java.lang.String)
	 */
	@Override
	public List<Object[]> getUser4flag(final String roleID) {
		String sql = new StringBuffer("SELECT a.userid,a.username,a.logonname,CASE b.roleId WHEN ? THEN 1 ELSE 2 END flag ")
		.append("FROM  elec_user a  LEFT JOIN elec_user_role b ON a.userID=b.userID AND b.roleId = ? WHERE a.isDuty = '1'").toString();
		return getSession().createSQLQuery(sql).setString(0, roleID).setString(1, roleID).list();
		/*
		 * return this.getHibernateTemplate().execute(new HibernateCallback() {
		 * public Object doInHibernate(Session session) throws HibernateException, SQLException { return session.createSQLQuery(sql).setString(0, roleID).setString(1, roleID).list();
		 * } });
		 */
	}

	/* (non-Javadoc)
	 * @see com.viapro.elec.dao.ElecUserRoleDao#deleteUserRoleByRoleId(java.lang.String)
	 */
	@Override
	public void deleteUserRoleByRoleId(String roleID) {
		String hql = "delete from ElecUserRole this where this.roleID=?";
		getSession().createQuery(hql).setString(0, roleID).executeUpdate();
	}

}
