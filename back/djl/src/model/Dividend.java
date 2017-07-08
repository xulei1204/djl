package model;

import java.util.ArrayList;

import com.jfinal.plugin.activerecord.Db;
import com.xiaheng.core.jfinal.BaseModel;

public class Dividend extends BaseModel<Dividend> {

	private static final long serialVersionUID = 1L;

	public static final Dividend dao = new Dividend();

	@Override
	public String tableName() {
		// TODO Auto-generated method stub
		return "a2017_djl_dividend";
	}

	@Override
	public String tableKey() {
		// TODO Auto-generated method stub
		return "id";
	}

	/**
	 * @方法名:获取单个用户提成累计
	 * @参数:type 1.一级提成 2.二级提成
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月4 11:06:48
	 * @修改:
	 */
	public double getDividendByUid(String type, String u) {

		StringBuffer sql = new StringBuffer();
		sql.append("select ifnull(sum(ifnull(money,0)),0) ");
		sql.append(" from " + this.tableName());
		sql.append(" where type =?");
		sql.append(" and superiorId=?");
		ArrayList<String> list = new ArrayList<String>();
		list.add(type);
		list.add(u);

		return Db.queryBigDecimal(sql.toString(), list.toArray()).doubleValue();

	}
}
