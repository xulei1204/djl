package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 每月抽奖奖池设置 model
 *
 * @version 1.0
 * @since 2017-6-19 9:47:37
 */
public class Lotto extends BaseModel<Lotto> {

	private static final long serialVersionUID = 1L;

	public static final Lotto dao = new Lotto();

	@Override
	public String tableName() {
		return "a2017_djl_lotto";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:本月是否开始抽奖
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 5:24:21
	 * @修改:
	 */
	public Record isOpenPrize() {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String month = format.format(now);
		JSONObject where = new JSONObject();
		where.put("month", month);
		Record result = this.showModel(where);
		return result;
	}

	/**
	 * @方法名:
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 5:41:11
	 * @修改:
	 */
}
