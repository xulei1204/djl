/*
 * @(#) config.java 2017年5月27日
 *
 * Copyright (c) 2015, XiaHeng NetWork. All Rights Reserved.
 * XiaHeng NetWork. CONFIDENTIAL
 */
package jfinal;

import javax.servlet.ServletContext;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.config.Interceptors;
import com.jfinal.core.JFinal;
import com.xiaheng.core.jfinal.BaseConfig;
import com.xiaheng.utils.PathConst;

public class Config extends BaseConfig {

	@Override
	protected void configConstPath(PathConst me) {
		me.setAdminViewPath("WEB-INF/page");
		me.setControllerPath("controller");
		me.setModelPath("model");
	}

	@Override
	public void afterJFinalStart() {
		ServletContext context = JFinal.me().getServletContext();
		context.setAttribute("sysUrl", JFinal.me().getContextPath() + "/admin/");// 后台路径
		// TODO Auto-generated method stub
		super.afterJFinalStart();
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.addGlobalActionInterceptor(new Interceptor() {
			@Override
			public void intercept(Invocation inv) {
				// TODO Auto-generated method stub
				inv.getController().getResponse()
						.setHeader("Access-Control-Allow-Origin", "*");
				inv.getController().getResponse()
						.setContentType("text/html; charset=utf-8");
				inv.invoke();
			}
		});
		
		super.configInterceptor(me);
	}

}
