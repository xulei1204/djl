package controller.api;

import model.Consume;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class ComsumeController extends BaseController {

	@Override
	protected String route() {
		return "api/consume";
	}

	/**
	 * @方法名:红包记录&提现记录
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月4 9:31:41
	 * @修改:
	 */
	public void showRecord() {
		int page = getParaToInt("page", 1);
		int pageSize = getParaToInt("pageSize", 10);
		String type = getPara("type", "1");
		String u = getPara("u", "");
		Consume model = new Consume();
		Page<Record> result = model.getRecordList(u, page, pageSize, type);
		if (result.getList().size() == 0) {
			renderJSON("203", "没有更多数据了");
		}
		renderJSON("200", "请求成功", result);
	}
}
