package com.viapro.elec.dao;

import java.io.Serializable;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.viapro.elec.bean.ElecText;
import com.viapro.elec.dao.impl.ElecApplicationDaoImpl;

public class TestDao {
/*	@Test
	public void testElecTextDao() {

		// 获得Spring容器
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecUserRoleDao eto = (ElecUserRoleDao) ac.getBean(ElecUserRoleDao.BEAN_NAME);
		ElecText et = new ElecText();
		et.setTextName("Adele");
		et.setTextDate(new Date());
		et.setTextRemark("008");
		// eto.save(et);

	}*/
	
	@Test
	public void review(){
		System.out.println(2 << 4);  
		System.out.println(-2 >> 4);
		System.out.println(2 >> 4); 
		System.out.println(-2 << 4);  
	}
	
	
	public void testArray(){
	}
}