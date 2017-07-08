package controller.api;

import model.Classify;

import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class ClassifyController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/classify";
	}

	/**
	 * @方法名：分类列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月28 9:32:26
	 * @修改:
	 */
	public void classifyList() {
		Classify model = new Classify();
		renderJSON(true, "请求成功", model.getList());
	}

}
