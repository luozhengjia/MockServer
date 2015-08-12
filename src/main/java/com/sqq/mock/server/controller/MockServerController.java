package com.sqq.mock.server.controller;

import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.sqq.mock.server.service.MockServerService;
import com.sqq.mock.server.util.MockServerUtil;

public class MockServerController extends Controller {
	
	public void access_token() {
		String ip = MockServerUtil.getUserIp(this.getRequest());
		Map<String, String> wxconfigMap = MockServerService.usermap.get(ip);
		String appId=getPara("appid");
		String openId= wxconfigMap.get(appId);
		
		Record result = new Record();
		result.set("openid", openId!=null?openId:"");
		renderJson(result);
	}

	public void authorize() {
		if (getPara("redirect_uri").contains("?")) {
			redirect(getPara("redirect_uri") + "&code=abcdefg&state=wx");
		} else {
			redirect(getPara("redirect_uri") + "?code=abcdefg&state=wx");
		}
	}

}
