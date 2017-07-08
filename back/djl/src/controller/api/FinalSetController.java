package controller.api;
import model.Finalset;
import com.alibaba.fastjson.JSONObject;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class FinalSetController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/finalset";
	}

	/**
	 * @方法名:抽奖首图片
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 3:50:29
	 * @修改:
	 */
	public void prizeIndex() {
		Finalset model = new Finalset();
		renderJSON(true, "请求成功", model.showModel("3", new JSONObject()));
	}

}
