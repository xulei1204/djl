package controller.admin;

import java.util.List;

import model.China;

import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

@RouteViewPath("admin")
public class ChinaController extends CRUD {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "admin/china";
	}

	/**
	 * @方法名:根据省显示城市
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月20 9:45:22
	 * @修改:
	 */

	public void cityList() {
		String proId = getPara("proId");
		if (proId == null || "undefined".equals(proId))
			return;
		China model = new China();
		List<Record> result = model.getCityList(proId);
		renderJSON(true, "请求成功", result);

	}
}
