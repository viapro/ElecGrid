package com.viapro.elec.service;

import com.viapro.elec.bean.ElecText;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class TestService {
	@Test
	public void testElecTextService(){
		
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ElecTextService ets = (ElecTextService) ac.getBean(ElecTextService.BEAN_NAME);
//		ElecTextService ets2 = (ElecTextService) ac.getBean(ElecTextService.BEAN_NAME);
		System.out.println(ets);
		
/*		ElecText et = new ElecText();
		et.setTextName("Albert Einstein");
		et.setTextDate(new Date());
		et.setTextRemark("009");
		ets.saveElecText(et);*/
	}
}
