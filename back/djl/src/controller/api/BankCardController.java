package controller.api;

import java.util.List;

import model.Bankcard;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import com.xiaheng.utils.ProjectUtil;

@RouteViewPath("api")
public class BankCardController extends BaseController {

	@Override
	protected String route() {
		return "api/bankcard";
	}

	/**
	 * @方法名:我的 银行卡列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月5 7:30:36
	 * @修改:
	 */
	public void myBankCard() {
		String uid = getPara("u", "");
		Bankcard model = new Bankcard();
		List<Record> list = model.showList(uid);
		for (Record re : list) {
			String code = re.getStr("code");
			re.set("code", ProjectUtil.replaceStr(code, 0, 4, '*'));
		}
		renderJSON(true, "请求成功", list);
	}

	/**
	 * @方法名:新增银行卡
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月5 8:00:35
	 * @修改:
	 */
	public void addBankCard() {
		String uid = getPara("u", "");
		String code = getPara("code", "");
		String name = getPara("name", "");
		String bankId = getPara("bankId", "");

		Bankcard model = new Bankcard();

		// 重复绑定验证
		JSONObject where = new JSONObject();
		where.put("memberId", uid);
		where.put("code", code);
		Record re = model.showModel(where);
		if (re != null) {
			renderJSON(false, "这张银行卡已经绑定过了");
			return;
		}

		model.set("memberId", uid);
		model.set("code", code);
		model.set("bankId", bankId);
		model.set("name", name);
		success = model.saveModel(model);
		if (success) {
			renderJSON(true, "添加成功");
			return;
		}
		renderJSON(false, "添加失败");

	}

	/**
	 * @方法名:删除银行卡
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月5 8:28:44
	 * @修改:
	 */
	public void delBankcard() {

	}

}
