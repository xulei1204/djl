package controller.admin;

import model.Consume;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

@RouteViewPath("admin")
public class ConsumeController extends CRUD {

	@Override
	protected String route() {
		return "admin/consume";
	}

	/**
	 * @方法名:显示列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月22 4:17:05
	 * @修改:
	 */
	public void showList() {
		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			// 显示类型
			String type = getPara("type");
			type = "0".equals(type) ? "" : type;
			// 用户id
			Record admin = (Record) getSession().getAttribute("admin");
			String uid = admin.getStr("U_id");
			Consume model = new Consume();
			JSONObject where = new JSONObject();
			where.put("type", type);
			where.put("U_id", uid);

			Page<Record> result = model.getPaging(page, pageSize, where);
			for (Record re : result.getList()) {
				// 类型
				if ("1".equals(String.valueOf(re.get("genre", "")))) {
					re.set("genre", "获得");
				} else {
					re.set("genre", "支出");
				}
				// 方式
				switch (String.valueOf(re.get("type", ""))) {
				case "1":
					re.set("type", "充值");
					break;
				case "2":
					re.set("type", "发布广告");
					break;
				default:
					break;
				}

			}
			renderJSON(true, "数据刷新成功", result);
		} else {
			renderJsp("consume/index.jsp");
		}
	}
}
