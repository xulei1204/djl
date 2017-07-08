package controller.api;

import model.Banner;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class BannerController extends BaseController {

	@Override
	protected String route() {

		return "api/banner";
	}

	/**
	 * @方法名:显示banner
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月27 2:09:45
	 * @修改:
	 */
	public void showBanner() {
		Banner model = new Banner();
		Page<Record> result = model.getPaging(1, 5);
		renderJSON(true, "请求成功", result.getList());
	}

}
