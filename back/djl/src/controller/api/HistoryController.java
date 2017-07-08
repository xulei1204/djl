package controller.api;

import model.Ad;
import model.History;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import com.xiaheng.utils.CodeBean;

@RouteViewPath("api")
public class HistoryController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/history";
	}

	/**
	 * @方法名:记录浏览历史
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月6 10:01:07
	 * @修改:
	 */
	public void recordHistory() {
		String uid = getPara("u", "");
		String ADID = getPara("ADID", "");
		History model = new History();
		Ad ad = new Ad();
		Record adDetail = ad.showModel(ADID, new JSONObject());
		// 删除之前的此条浏览记录
		JSONObject where = new JSONObject();
		where.put("ADID", ADID);
		where.put("memberId", uid);
		Record re = model.showModel(where);
		if (re != null) {
			Db.delete(model.tableName(), re);
		}
		where.put("title", adDetail.get("title"));

		// 浏览记录是否满，是，则返回最早一条的记录id
		CodeBean<String> bean = model.isFull(uid);
		if (bean.isSuccess()) {
			where.put(model.tableKey(), bean.getData());
		}

		renderJSON(model.modifyModel(where));
	}

	/**
	 * @方法名:我的浏览记录
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月6 11:11:28
	 * @修改:
	 */
	public void showList() {
		String uid = getPara("u", "");
		History model = new History();
		JSONObject where = new JSONObject();
		where.put("memberId", uid);
		Page<Record> result = model.getPaging(1, 15, where);
		renderJSON(true, "请求成功", result.getList());
	}
}
