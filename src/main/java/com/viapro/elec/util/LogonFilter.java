/**
 * 
 */
package com.viapro.elec.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogonFilter implements Filter {
	private Set<String> urls = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		urls = new HashSet<String>();
		urls.add("/index.jsp");
		urls.add("/image.jsp");
		urls.add("/error.jsp");
		urls.add("/close.jsp");
		urls.add("/system/elecMenuAction_home.do");
		urls.add("/system/elecMenuAction_exit.do");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request0, ServletResponse response0, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) request0;
		HttpServletResponse response = (HttpServletResponse) response0;
		// 1.从requet中取出cookie
		initRemeberMe(request, response);
		if (urls.contains(request.getServletPath())) {
			chain.doFilter(request, response);
			return;
		}
		Set<String> userUrls = (Set<String>) request.getSession().getAttribute("globel_urls");
		if (userUrls != null && userUrls.size() > 0 && userUrls.contains(request.getServletPath())) {
			chain.doFilter(request, response);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/error.jsp");
			return;
		}
	}

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 下午10:06:23
	 * @Parameters:
	 * @Return:void
	 */
	private void initRemeberMe(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getServletPath();
		if (url.equals("/index.jsp")) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("name")) {
						try {
							request.setAttribute("name", URLDecoder.decode(cookie.getValue(), "utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} else if (cookie.getName().equals("password")) {
						request.setAttribute("password", cookie.getValue());
					}
				}
			}
		}
	}

	@Override
	public void destroy() {
	}

}
