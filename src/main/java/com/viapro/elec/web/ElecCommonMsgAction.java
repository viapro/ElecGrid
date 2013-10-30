package com.viapro.elec.web;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.viapro.elec.bean.ElecCommonMsg;
import com.viapro.elec.service.ElecCommonMsgService;

@Controller("elecCommonMsgAction")
@Scope("prototype")
public class ElecCommonMsgAction extends BaseAction<ElecCommonMsg> {

    private ElecCommonMsgService elecCommonMsgService;

    @Resource(name=ElecCommonMsgService.BEAN_NAME)
    public void setElecCommonMsgService(ElecCommonMsgService elecCommonMsgService) {
        this.elecCommonMsgService = elecCommonMsgService;
    }

    //在这进行回显的代码设置！
    public String home(){
		ElecCommonMsg msg = elecCommonMsgService.getCommonMsg();
		ServletActionContext.getContext().getValueStack().pop();
		ServletActionContext.getContext().getValueStack().push(msg);
        return "home";
    }

	public String save(){
        elecCommonMsgService.save(model);
		return "save";
	}

}
