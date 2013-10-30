package com.viapro.elec.web;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.viapro.elec.bean.ElecCommonMsg;
import com.viapro.elec.bean.ElecRolePopedom;
import com.viapro.elec.bean.ElecUser;
import com.viapro.elec.bean.ElecUserRole;
import com.viapro.elec.service.ElecCommonMsgService;
import com.viapro.elec.service.ElecRoleService;
import com.viapro.elec.service.ElecUserService;
import com.viapro.elec.util.CookieUtil;
import com.viapro.elec.util.MD5keyBean;
import com.viapro.elec.vo.FunctionXml;
import com.viapro.elec.vo.User;

@Controller("elecMenuAction")
@Scope("prototype")
public class ElecMenuAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;

	private ElecCommonMsgService elecCommonMsgService;

	private ElecUserService userService;

	private ElecRoleService roleService;

	@Resource(name = ElecRoleService.BEAN_NAME)
	public void setRoleService(ElecRoleService roleService) {
		this.roleService = roleService;
	}

	@Resource(name = ElecUserService.BEAN_NAME)
	public void setUserService(ElecUserService userService) {
		this.userService = userService;
	}

	@Resource(name = ElecCommonMsgService.BEAN_NAME)
	public void setElecCommonMsgService(ElecCommonMsgService elecCommonMsgService) {
		this.elecCommonMsgService = elecCommonMsgService;
	}

	public String toIndex() {
		return "toIndex";
	}

	/**
	 * @Name:home
	 * @Description:most operations of user login
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-9-28 PM1:04:15
	 * @Parameters:~
	 * @Return:String
	 */
	public String home() {
		// 用户名或密码不能为空
		StringBuffer error = new StringBuffer();
		boolean haveError = false;
		if (StringUtils.isEmpty(model.getName()) || StringUtils.isEmpty(model.getPassword()) || StringUtils.isEmpty(model.getCheckNumber())) {
			if (StringUtils.isEmpty(model.getName())) {
				error.append("用户名");
				haveError = true;
			}
			if (StringUtils.isEmpty(model.getPassword())) {
				error.append(haveError ? "、" : "").append("密码");
				haveError = true;
			}
			// 验证码
			if (StringUtils.isEmpty(model.getCheckNumber())) {
				error.append(haveError ? "及" : "").append("验证码");
				haveError = true;
			}
			addFieldError(LOGIN, error.append("不能为空！").toString());
			return LOGIN;
		}
		Object sessionCheckNumber = request.getSession().getAttribute("CHECK_NUMBER_KEY");
		if (sessionCheckNumber == null) {
			addFieldError(LOGIN, "验证码过期！");
			return LOGIN;
		}
		if (!sessionCheckNumber.toString().equals(model.getCheckNumber())) {
			addFieldError(LOGIN, "验证码有误！");
			return LOGIN;
		}
		// 检查用户名是否存在及用户密码是否正确
		ElecUser user = userService.getUserByLogonName(model.getName());
		if (user == null) {
			addFieldError(LOGIN, "登录名不存在！");
			return LOGIN;
		} else if (!new MD5keyBean().getkeyBeanofStr(model.getPassword()).equals(user.getLogonPwd())) {
			addFieldError(LOGIN, "密码错误！");
			return LOGIN;
		}
		// 查询用户的角色类型的列表
		Map<String, String> userRoleMap = userService.getRoleMapByLogonName(model.getName());
		if (userRoleMap == null || userRoleMap.keySet().size() < 1) {
			addFieldError(LOGIN, "您不具备任何角色类型！");
			return LOGIN;
		}
		// 根据角色类的列表获得权限列表
		String userPopedom = userService.getResourceByRoleIds(userRoleMap.keySet());
		if (userPopedom == null || StringUtils.isBlank(userPopedom)) {
			addFieldError(LOGIN, "您缺少必须的系统权限！");
			return LOGIN;
		}
		// 根据需求来处理Cookie
		CookieUtil.remberMe(model, request, response);
		// 获得登录用户拥有访问路径的集合
		Set<String> urls = new HashSet<String>();
		for (String roleID : userRoleMap.keySet()) {
			ElecUserRole role = new ElecUserRole();
			role.setRoleID(roleID);
			for (FunctionXml fx : roleService.getAllPopedom4flag(role))
				if ("1".equals(fx.getFlag()))
					urls.add(fx.getUrl());
		}

		ServletActionContext.getContext().getSession().put("globel_user", user);
		ServletActionContext.getContext().getSession().put("globel_role", userRoleMap);
		ServletActionContext.getContext().getSession().put("globel_popedom", userPopedom);
		ServletActionContext.getContext().getSession().put("globel_urls", urls);

		return "home";
	}

	public String exit() {
		request.getSession().invalidate();
		return "exit";
	}

	public String title() {
		return "title";
	}

	public String left() {
		return "left";
	}

	public String change() {
		return "change";
	}

	public String loading() {
		return "loading";
	}

	public String alermAdjust() {
		return "alermAdjust";
	}

	public String alermDevice() {
		ElecCommonMsg msg = elecCommonMsgService.getCommonMsg();
		ServletActionContext.getContext().getValueStack().pop();
		ServletActionContext.getContext().getValueStack().push(msg);
		return "alermDevice";
	}

	public String alermRepair() {
		return "alermRepair";
	}

	public String alermStation() {
		ElecCommonMsg msg = elecCommonMsgService.getCommonMsg();
		ServletActionContext.getContext().getValueStack().pop();
		ServletActionContext.getContext().getValueStack().push(msg);
		return "alermStation";
	}

}
