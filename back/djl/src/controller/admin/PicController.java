package controller.admin;

import model.Pic;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

@RouteViewPath("admin")
public class PicController extends CRUD {

	@Override
	protected String route() {
		return "admin/pic";
	}

	/**
	 * @方法名:公司展示图片
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月19 2:44:46
	 * @修改:
	 */
	public void showlistForMyspace() {
		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			Record admin = (Record) getSession().getAttribute("admin");
			String uid = admin.getStr("U_id");// 业主用户id
			JSONObject where = new JSONObject();
			where.put("relationId", uid);
			where.put("type", "1");
			Pic model = new Pic();
			Page<Record> result = model.getPaging(page, pageSize, where);
			renderJSON(true, "数据已更新", result);
		} else {
			renderJsp("pic/index.jsp");
		}

	}

}
