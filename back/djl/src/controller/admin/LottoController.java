package controller.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import model.Lotto;
import model.LottoRecord;
import model.Member;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;
import com.xiaheng.utils.CodeBean;

@RouteViewPath("admin")
public class LottoController extends CRUD {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "admin/lotto";
	}

	/**
	 * @方法名:每月抽奖列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月6 8:15:22
	 * @修改:
	 */
	public void showList() {
		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			Lotto model = new Lotto();
			Page<Record> result = model.getPaging(page, pageSize);
			String statusStr = "";
			for (Record re : result.getList()) {
				statusStr = re.getInt("status") > 1 ? "已开奖" : "未开奖";
				re.set("statusStr", statusStr);
			}
			renderJSON(true, "请求成功", result);
		} else {
			renderJsp("lotto/index.jsp");
		}

	}

	/**
	 * @方法名:设置本月抽奖奖金
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 9:14:28
	 * @修改:
	 */

	public void setLotto() {
		String where = getPara("where");
		JSONObject json = JSONObject.parseObject(where);
		// 当前时间
		Date now = new Date();
		SimpleDateFormat formatMonth = new SimpleDateFormat("yyyy-MM");
		String month = formatMonth.format(now);

		Lotto model = new Lotto();
		JSONObject params = new JSONObject();

		if (StringUtils.isBlank(json.getString("id"))) {
			// 查询当月是否已设置
			params.put("month", month);
			Record re = model.showModel(params);
			if (re != null) {
				renderJSON(false, "本月抽奖有设置，请前往列表页修改");
				return;
			}
		} else {
			Record re = model.showModel(json.getString("id"), new JSONObject());
			re.getStr("month");
			if (!re.getStr("month").equals(month)) {
				renderJSON(false, "只能修改本月未开奖的奖金！");
				return;
			}
		}

		Calendar ca = Calendar.getInstance();
		ca.setTime(now);
		int day = ca.get(Calendar.DAY_OF_MONTH);
		if (day > 10) {
			renderJSON(false, "已经过了开奖期，无法设置(修改)每月抽奖");
			return;
		}
		Double money = 0d;

		try {
			money = Double.parseDouble(json.getString("money"));
		} catch (Exception e) {
			renderJSON(false, "奖金不是一个正确的数值");
			return;
		}

		// 添加每月抽奖
		params.clear();
		params.put("id", json.getString("id"));
		params.put("money", money);
		params.put("month", month);
		CodeBean<?> bean = model.modifyModel(params);
		renderJSON(bean);

	}

	/**
	 * @方法名:每月抽奖开奖
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 1:43:01
	 * @修改:
	 */
	public void openLotto() {
		String lottoId = getPara("id");
		Lotto model = new Lotto();
		Record re = model.showModel(lottoId, new JSONObject());
		if (re == null) {
			renderJSON(false, "活动已不存在");
			return;
		}
		re.set("status", "2");
		// 查询参与用户
		LottoRecord lottoRe = new LottoRecord();
		List<Record> mebList = lottoRe.allMebRecord(lottoId);
		if (mebList == null || mebList.size() == 0) {
			renderJSON(false, "本期获得参与人数不足1,未能有人中奖！");
			Db.update(model.tableName(), model.tableKey(), re);
			return;
		}
		Random rand = new Random();
		Record luckOne = new Record();// 参与记录
		Record luckMeb = null;// 参与会员
		Member member = new Member();
		while (luckMeb == null) {
			int num = rand.nextInt(mebList.size());
			luckOne = mebList.get(num);
			luckMeb = member.showModel(luckOne.getStr("memberId"),
					new JSONObject());
		}
		// 抽中中奖者
		Double money = re.getBigDecimal("money").doubleValue();
		luckMeb.set("money", luckMeb.getBigDecimal("money").doubleValue()
				+ money);
		luckOne.set("status", "1");
		if (Db.update(member.tableName(), member.tableKey(), luckMeb)
				&& Db.update(lottoRe.tableName(), lottoRe.tableKey(), luckOne)) {
			renderJSON(true, "已开奖！中奖用户为 " + luckMeb.getStr("nickname") + "("
					+ luckMeb.getStr("tel") + ") ");
			Db.update(model.tableName(), model.tableKey(), re);
			return;
		}
		renderJSON(false, "系统异常！");
	}
}
