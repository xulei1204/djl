package model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;
import com.xiaheng.utils.CodeBean;

public class History extends BaseModel<History> {

	private static final long serialVersionUID = 1L;

	@Override
	public String tableName() {
		// TODO Auto-generated method stub
		return "a2017_djl_history";
	}

	@Override
	public String tableKey() {
		// TODO Auto-generated method stub
		return "id";
	}

	/**
	 * @方法名:浏览记录是否满
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月6 10:08:00
	 * @修改:
	 */
	public CodeBean<String> isFull(String uid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from " + this.tableName());
		sql.append(" where memberId=?");
		long count = Db.queryLong(sql.toString(), uid);
		CodeBean<String> bean = new CodeBean<String>();
		if (count > 15) {
			bean.setSuccess(true);
			sql.setLength(0);
			sql.append("select " + tableKey() + " from " + this.tableName());
			sql.append(" where memberId=?");
			sql.append(" order by addTime asc");
			Record re = Db.findFirst(sql.toString(), uid);
			bean.setData(re.getStr(tableKey()));
			return bean;
		}
		bean.setSuccess(false);
		return bean;
	}

}
