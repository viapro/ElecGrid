package com.viapro.elec;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.viapro.elec.bean.ElecText;
import com.viapro.elec.util.HibernateSessionFactory;

public class TestHibernate {
	@Test
	public void testSessionFactory(){
		
		
		
//		Configuration cfg = new Configuration().configure();
//		SessionFactory sf = cfg.buildSessionFactory(new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry());
//		SessionFactory sf = cfg.buildSessionFactory();
//		Session session = sf.openSession();
        Session session = HibernateSessionFactory.getSession();
        Transaction ts = session.beginTransaction();
        
        
        ElecText et = new ElecText();
		et.setTextName("Kirito");
		et.setTextDate(new Date());
		et.setTextRemark("001");
		session.persist(et);
		
		
		session.flush();
		ts.commit();
		
		
		
	}
}