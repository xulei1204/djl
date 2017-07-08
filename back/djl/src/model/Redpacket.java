package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.project.LeftMoneyPackage;
import utils.project.MoneyPackage;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;
import com.xiaheng.utils.CodeBean;

/**
 * 红包 model
 *
 * @version 1.0
 * @since 2017-6-14 15:34:49
 */
public class Redpacket extends BaseModel<Redpacket> {

	private static final long serialVersionUID = 1L;

	public static final Redpacket dao = new Redpacket();

	@Override
	public String tableName() {
		return "a2017_djl_redpacket";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:随机获得一种红包
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月30 9:42:51
	 * @修改:
	 */
	public CodeBean<JSONObject> getPackageType(String ADID) {
		CodeBean<JSONObject> bean = new CodeBean<JSONObject>();
		// 获取红包剩余情况
		StringBuffer sql = new StringBuffer();
		sql.append("select id,maxiamlSurplus,secondSurplus,randomSurplus,vacantSurplus,(maxiamlSurplus+secondSurplus+randomSurplus+vacantSurplus) as count,maximalMoney,secondMoney ");

		sql.append("from " + this.tableName());
		sql.append(" where ADID=?");

		Record stepOne = Db.findFirst(sql.toString(), ADID);
		if (stepOne == null) {
			bean.setSuccess(false);
			bean.setCode("204");
			bean.setMessage("红包已失效！");
			return bean;
		}
		if (stepOne.getLong("count") <= 0) {
			bean.setSuccess(false);
			bean.setMessage("红包已经被领完啦！");
			return bean;
		}

		// 随机获得一种没被领取完的红包(1.最大红包 2.次大红包 3.随机红包 4.空红包)
		List<Integer> list = new ArrayList<Integer>();
		if (stepOne.getInt("maxiamlSurplus") > 0) {
			list.add(1);
		}
		if (stepOne.getInt("secondSurplus") > 0) {
			list.add(2);
		}
		if (stepOne.getInt("randomSurplus") > 0) {
			list.add(3);
		}
		if (stepOne.getInt("vacantSurplus") > 0) {
			list.add(4);
		}
		Random rand = new Random();
		int num = rand.nextInt(list.size());
		bean.setMessage("设定成功");
		bean.setSuccess(true);
		JSONObject obj = new JSONObject();
		obj.put("id", stepOne.get("id"));
		obj.put("type", list.get(num));
		obj.put("maximalMoney", stepOne.get("maximalMoney"));
		obj.put("secondMoney", stepOne.get("secondMoney"));
		bean.setData(obj);
		return bean;
	}

	/**
	 * @方法名:获取随机红包
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月30 10:31:16
	 * @修改:
	 */
	public double getRomdomMoney(String ADID) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,rMonSurplus,randomSurplus ");
		sql.append("from " + this.tableName());
		sql.append(" where id=?");
		Record result = Db.findFirst(sql.toString(), ADID);
		Double rMonSurplus = result.getBigDecimal("rMonSurplus").doubleValue();
		int randomSurplus = result.getInt("randomSurplus");
		if (rMonSurplus <= 0 || rMonSurplus <= 0) {
			return 0.0;
		}
		LeftMoneyPackage pack = new LeftMoneyPackage();
		pack.setRemainMoney(rMonSurplus);
		pack.setRemainSize(randomSurplus);
		// 更新红包剩余
		double romdomMoney = MoneyPackage.getRandomMoney(pack);
		result.set("rMonSurplus", pack.getRemainMoney());
		result.set("randomSurplus", pack.getRemainSize());
		Db.update(this.tableName(), result);
		return romdomMoney;
	}

	/**
	 * @方法名：更新红包剩余
	 * @参数:type 1.最大红包 2.次大红包 3.空红包
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月30 3:08:02
	 * @修改:
	 */
	public boolean updateSurplus(int type, String id) {
		Record re = this.showModel(id, new JSONObject());
		switch (type) {
		case 1:
			re.set("maxiamlSurplus", re.getInt("maxiamlSurplus") - 1);
			break;
		case 2:
			re.set("secondSurplus", re.getInt("secondSurplus") - 1);
			break;
		case 3:
			re.set("vacantSurplus", re.getInt("vacantSurplus") - 1);
			break;

		default:
			break;
		}
		return Db.update(this.tableName(), re);
	}
}
