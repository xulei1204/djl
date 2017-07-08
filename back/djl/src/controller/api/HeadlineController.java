package controller.api;

import model.Headline;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class HeadlineController extends BaseController {

	@Override
	protected String route() {
		return "api/headline";
	}

	/**
	 * @方法名:最新头条
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月28 9:50:53
	 * @修改:
	 */
	public void showNewest() {
		Headline model = new Headline();
		Record result;
		if ((result = model.getNewest()) != null) {
			renderJSON(true, "请求成功", result);
			return;
		}
		renderJSON(false, "没有数据");
	}

	/**
	 * @方法名:头条列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月28 5:38:58
	 * @修改:
	 */
	public void showList() {
		int page = getParaToInt("page", 1);
		int pageSize = getParaToInt("pageSize", 10);
		Headline model = new Headline();
		Page<Record> result = model.getPaging(page, pageSize, new JSONObject());
		renderJSON("200", "请求成功", result);

	}

	/**
	 * @方法名:头条详情
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月28 7:13:29
	 * @修改:
	 */
	public void showDetail() {
		String id = getPara("id");
		if (StringUtils.isBlank(id)) {
			renderJSON(false, "请求错误");
			return;
		}
		Headline model = new Headline();
		Record result = model.showModel(id, new JSONObject());
		renderJSON(true, "请求成功", result);
	}
}
