package com.viapro.elec.web;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,
		ServletRequestAware, ServletResponseAware {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	protected T model;

	protected BaseAction() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class clazz = (Class) pt.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return model;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
