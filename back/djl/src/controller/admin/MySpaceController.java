package controller.admin;

import model.Myspace;

import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

@RouteViewPath("admin")
public class MySpaceController extends CRUD {

	@Override
	protected String route() {

		return "admin/myspace";
	}

	/**
	 * @方法名:我的空间
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月19 11:57:45
	 * @修改:
	 */
	public void showMyscpace() {
		Record admin = (Record) getSession().getAttribute("admin");
		String uid = admin.getStr("U_id");// 业主用户id
		Myspace model = new Myspace();
		Record info = model.getModelByUid(uid);
		if (isPost()) {
			renderJSON(true, "请求成功", info);
			return;
		}
		setAttr("v", info);
		renderJsp("myspace/modify.jsp");

	}
}
