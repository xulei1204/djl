package controller.api;

import model.About;
import model.Feedback;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class AboutController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/about";
	}

	/**
	 * @方法名:根据id显示单篇文章
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月4 3:19:06
	 * @修改:
	 */
	public void showAbout() {
		String aboutId = getPara("aboutId", "");
		About model = new About();
		renderJSON(true, "请求成功", model.showModel(aboutId, new JSONObject()));
	}

	/**
	 * @方法名:常见问题&全部问题&我的反馈
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月4 3:52:37
	 * @修改:
	 */
	public void showQuestionList() {

		String type = getPara("type", "");// 2.常见问题 3.全部问题 1.我的反馈
		String u = getPara("u", "");
		int page = getParaToInt("page");
		int pageSize = getParaToInt("pageSize");

		Page<Record> result = new Page<Record>();
		JSONObject where = new JSONObject();

		if (!"1".equals(type)) {
			About model = new About();
			where.put("type", type);
			result = model.getPaging(page, pageSize, where);
		} else {
			Feedback model = new Feedback();
			where.put("memberId", u);
			result = model.getPaging(page, pageSize, where);
			String str = "";
			for (Record re : result.getList()) {
				if ((str = re.getStr("content")).length() > 10) {
					re.set("content", str.substring(0, 10));
				}
			}
		}
		if (result.getList().size() == 0) {
			renderJSON("203", "没有更多数据了");
			return;
		}
		renderJSON("200", "请求成功", result);

	}
}
