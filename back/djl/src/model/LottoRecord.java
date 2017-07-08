package model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

public class LottoRecord extends BaseModel<LottoRecord> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String tableName() {
		// TODO Auto-generated method stub
		return "a2017_djl_lotto_record";
	}

	@Override
	public String tableKey() {
		// TODO Auto-generated method stub
		return "id";
	}

	/**
	 * @方法名:列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 11:06:07
	 * @修改:
	 */
	public Page<Record> getList(int page, int pageSize, String lottoId) {
		StringBuffer select = new StringBuffer();
		select.append("select record.id,date_format(record.addTime,'%Y-%m-%d %H:%i') as time,record.status,member.nickname,member.tel ");
		StringBuffer from = new StringBuffer();
		from.append(" from " + this.tableName() + " as record");
		from.append(" left join " + new Member().tableName() + " as member");
		from.append(" on record.memberId=member.U_id ");
		from.append(" where record.isDel='0' and record.lottoId=?");
		return Db.paginate(page, pageSize, select.toString(), from.toString(),
				lottoId);
	}

	/**
	 * @方法名:根据每月抽奖id获得所有参与会员
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 1:49:04
	 * @修改:
	 */
	public List<Record> allMebRecord(String lottoId) {

		StringBuffer sql = new StringBuffer();
		sql.append("select memberId,id from ");
		sql.append(this.tableName());
		sql.append(" where lottoId=? and isDel='0'");
		return Db.find(sql.toString(), lottoId);

	}

	/**
	 * @方法名:查询用户本月剩余抽奖机会
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 5:09:26
	 * @修改:
	 */
	public int queryPrizeSurplus(String uid, String lottoId) {

		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from " + this.tableName());
		sql.append(" where memberId=? and lottoId=?");
		ArrayList<String> list = new ArrayList<String>();
		list.add(uid);
		list.add(lottoId);
		long count = Db.queryLong(sql.toString(), list.toArray());
		return 3 - (int) count;
	}

}
