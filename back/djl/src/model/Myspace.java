package model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * model
 *
 * @version 1.0
 * @since 2017-6-13 11:53:06
 */
public class Myspace extends BaseModel<Myspace> {

	private static final long serialVersionUID = 1L;

	public static final Myspace dao = new Myspace();

	@Override
	public String tableName() {
		return "a2017_djl_myspace";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:根据uid查找model
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月19 1:49:18
	 * @修改:
	 */
	public Record getModelByUid(String uid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select U_id,tel,linkman,`e_mail`,address,intro from ");
		sql.append(this.tableName());
		sql.append(" where ");
		sql.append(" U_id=? ");

		return Db.findFirst(sql.toString(), uid);
	}
}
