package com.viapro.elec.workflow;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActivitiTest {

	@Test
	public void deploy() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		RepositoryService repositoryService = (RepositoryService) ac.getBean("repositoryService");
		RuntimeService runtimeService = (RuntimeService) ac.getBean("runtimeService");
		
		repositoryService.createDeployment().addClasspathResource("VacationRequest.bpmn20.xml").deploy();
		// Start a process instance
//		runtimeService.startProcessInstanceByKey("vacationRequest");
	}

}
