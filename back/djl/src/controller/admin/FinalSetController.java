package controller.admin;

import model.Finalset;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

@RouteViewPath("admin")
public class FinalSetController extends CRUD {

	@Override
	protected String route() {

		return "admin/finalset";
	}

	/**
	 * @方法名:显示model
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月16 9:26:03
	 * @修改:
	 */
	public void showModel() {
		String id = getPara("id");
		Finalset model = new Finalset();
		Record result = model.showModel(id, new JSONObject());
		if (isPost()) {
			renderJSON(true, "请求成功", result);
		} else {
			setAttr("v", result);
			switch (id) {
			case "2":
				renderJsp("finalset/ratio.jsp");
				break;
			case "3":
				renderJsp("finalset/backgroundSet.jsp");
				break;
			case "4":
				renderJsp("finalset/backgroundSet.jsp");
				break;
			case "5":
				renderJsp("finalset/aboutSet.jsp");
				break;
			case "6":
				renderJsp("finalset/dividendSet.jsp");
				break;
			case "7":
				renderJsp("finalset/dividendSet.jsp");
				break;
			default:
				renderError(404);
			}

		}

	}

}
