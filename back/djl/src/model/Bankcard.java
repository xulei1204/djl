package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 绑定银行卡 model
 *
 * @version 1.0
 * @since 2017-7-5 19:25:52
 */
public class Bankcard extends BaseModel<Bankcard> {

	private static final long serialVersionUID = 1L;

	public static final Bankcard dao = new Bankcard();

	@Override
	public String tableName() {
		return "a2017_djl_bankcard";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:银行卡列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月5 7:33:20
	 * @修改:
	 */
	public List<Record> showList(String uid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select card.`code`,bank.bankName,bank.pic");
		sql.append(" from " + this.tableName() + " as card ");
		sql.append(" left join " + new Bank().tableName() + " as bank");
		sql.append(" on card.bankId=bank.id ");
		sql.append(" where card.memberId=?");

		return Db.find(sql.toString(), uid);
	}

}
