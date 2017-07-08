package model;

import java.util.ArrayList;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 消费明细 model
 *
 * @version 1.0
 * @since 2017-6-21 19:31:29
 */
public class Consume extends BaseModel<Consume> {

	private static final long serialVersionUID = 1L;

	public static final Consume dao = new Consume();

	@Override
	public String tableName() {
		return "a2017_djl_consume";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:红包记录
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月3 9:34:39
	 * @修改:
	 */
	public Page<Record> getRecordList(String u, int page, int pageSize,
			String type) {
		StringBuffer select = new StringBuffer();
		select.append("select type,genre,date_format(addTime,'%Y-%m-%d %H:%i') as time,money ");
		StringBuffer from = new StringBuffer();
		from.append(" from " + this.tableName());
		from.append(" where isDel=0 and U_id=?");
		ArrayList<String> list = new ArrayList<String>();
		list.add(u);
		if ("1".equals(type)) {
			from.append(" and (type=3 or type=4)");
		} else {
			from.append(" and type=?");
			list.add(type);
		}
		return Db.paginate(page, pageSize, select.toString(), from.toString(),
				list.toArray());
	}

	/**
	 * @方法名:各个方式累计收益&支出
	 * @参数:type 1.充值 2.发布广告（红包） 3.领取红包 4.提现 5.一级提成 6.二级提成
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月4 1:49:16
	 * @修改:
	 */
	public double getConsumeSum(String u, String type) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ifnull(sum(ifnull(money,0.00)),0.00) ");
		sql.append("from " + this.tableName());
		sql.append(" where U_id=? and type=?");
		ArrayList<String> list = new ArrayList<String>();
		list.add(u);
		list.add(type);
		return Db.queryBigDecimal(sql.toString(), list.toArray()).doubleValue();
	}
}
