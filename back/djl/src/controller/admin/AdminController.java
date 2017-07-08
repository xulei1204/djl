package controller.admin;

import model.Admin;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

@RouteViewPath("admin/")
public class AdminController extends CRUD {

	@Override
	protected String route() {
		return "/admin/admin";
	}

	/**
	 * @方法名:用户列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月13 5:34:07
	 * @修改:
	 */
	public void showAdminList() {
		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			Page<Record> result = new Admin().getList(page, pageSize);
			renderJSON(true, "数据已更新", result);

		} else {
			renderJsp("admin/index.jsp");
		}
	}

}
