package controller.api;

import model.Bank;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class BankController extends BaseController {

	@Override
	protected String route() {
		return "api/bank";
	}

	/**
	 * @方法名:选择银行列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月6 2:56:19
	 * @修改:
	 */
	public void bankList() {

		Bank model = new Bank();
		Page<Record> page = model.getPaging(1, 20);
		renderJSON(true, "请求成功", page.getList());
	}
}
