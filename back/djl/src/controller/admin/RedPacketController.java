package controller.admin;

import model.Ad;
import model.Member;
import model.Redpacket;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.HttpCode;

@RouteViewPath("admin")
public class RedPacketController extends CRUD {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "admin/redpacket";
	}

	/**
	 * @方法名:设置红包页面
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月20 4:33:37
	 * @修改:
	 */
	public void setRedPacketIndex() {
		String ADID = getPara("ADID");
		setAttr("ADID", ADID);
		renderJsp("redpacket/modify.jsp");
	}

	/**
	 * @方法名:保存红包设置并发布广告
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月21 10:35:41
	 * @修改:
	 */
	public void publish() {
		String where = getPara("where");
		JSONObject json = new JSONObject();
		if (StringUtils.isBlank(where)) {
			renderJSON(HttpCode.UNSUPPORTED, "操作失败");
			return;
		}
		json = JSONObject.parseObject(where);
		// 验证账户余额
		Record admin = (Record) getSession().getAttribute("admin");
		String U_id = admin.get("U_id");
		String total = json.getString("total");
		Member member = new Member();
		CodeBean<Record> checkMoney = member.checkMoney(U_id, total);
		if (!checkMoney.isSuccess()) {
			renderJSON(checkMoney);
			return;
		}
		Redpacket model = new Redpacket();
		// 红包剩余数量初始化
		json.put("maxiamlSurplus", json.get("maximalNum"));
		json.put("secondSurplus", json.get("secondNum"));
		json.put("randomSurplus", json.get("randomNum"));
		json.put("vacantSurplus", json.get("vacantNum"));
		// 随机红包剩余金额初始化
		json.put("rMonSurplus", json.get("randomMoney"));

		CodeBean<?> bean = model.modifyModel(json);
		if (!bean.isSuccess()) {
			bean.setMessage("发布失败");
			renderJSON(bean);
			return;
		}

		// 扣除账户余额
		Double balance = Double.parseDouble(checkMoney.getData().get("money")
				.toString())
				- Double.parseDouble(total);
		JSONObject params = new JSONObject();
		params.put("U_id", U_id);
		params.put("money", balance);
		member.modifyModel(params);
		// 发布广告
		Ad adModel = new Ad();
		params = new JSONObject();
		params.put("id", json.getString("ADID"));
		params.put("isDel", "0");
		adModel.modifyModel(params);
		bean.setMessage("发布成功！");
		renderJSON(bean);

	}

}
