package controller.api;

import model.Consume;
import model.Member;
import model.Redpacket;
import model.RedpacketRecord;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import com.xiaheng.utils.CodeBean;

@RouteViewPath("api")
public class RedPacketController extends BaseController {

	@Override
	protected String route() {
		return "api/redpacket";
	}

	/**
	 * @方法名:拆红包
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月30 9:23:45
	 * @修改:
	 */
	public void openRedpacket() {
		String ADID = getPara("ADID", "");
		String u = getPara("u", "");
		// 判断领取红包资格
		RedpacketRecord reModel = new RedpacketRecord();
		CodeBean<?> check = reModel.checkRecord(u, ADID);
		if (!check.isSuccess()) {
			renderJSON(check);
			return;
		}
		Redpacket model = new Redpacket();
		// 随机获得一种红包(1.最大红包 2.次大红包 3.随机红包 4.空红包)
		CodeBean<JSONObject> bean = model.getPackageType(ADID);
		JSONObject redPacket = bean.getData();
		String redId = redPacket.getString("id");
		int type = redPacket.getInteger("type");
		Double money = 0.00d;
		JSONObject obj = new JSONObject();
		if (!bean.isSuccess()) {
			renderJSON(bean);
		}
		switch (type) {
		case 1:// 最大红包
			model.updateSurplus(1, redId);
			money = redPacket.getDouble("maximalMoney");
			obj.put("money", money);
			obj.put("type", 1);
			obj.put("describe", "最大红包");

			break;
		case 2:// 次大红包
			model.updateSurplus(2, redId);
			money = redPacket.getDouble("secondMoney");
			obj.put("money", money);
			obj.put("type", 2);
			obj.put("describe", "次大红包");
			break;
		case 3:// 随机红包
			money = model.getRomdomMoney(redId);
			obj.put("money", money);
			obj.put("type", 3);
			obj.put("describe", "随机");
			break;
		case 4:// 空红包
			model.updateSurplus(3, redId);
			break;
		default:
			break;
		}

		// 如果是空红包
		if (redPacket.getInteger("type") == 4) {
			renderJSON("201", "真遗憾，红包里面是空的！");
			return;
		}
		// 将红包金额增加到用户余额
		Record member = new Member().showModel(u, new JSONObject());
		member.set("money", member.getBigDecimal("money").doubleValue() + money);
		Member memberModel = new Member();
		if (Db.update(memberModel.tableName(), memberModel.tableKey(), member)) {
			// 红包记录
			reModel.clear();
			reModel.set("ADID", ADID);
			reModel.set("memberId", u);
			reModel.set("redId", redId);
			reModel.set("type", redPacket.get("type"));
			reModel.set("money", money);
			reModel.saveModel(reModel);

			// 消费明细
			Consume consume = new Consume();
			consume.set("type", 3);
			consume.set("U_id", u);
			consume.set("genre", 1);
			consume.set("money", money);
			consume.saveModel(consume);
			renderJSON(true, "领取成功", obj);
			return;
		}
		renderJSON(false, "领取失败");
	}
}
