package model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 头条 model
 *
 * @version 1.0
 * @since 2017-6-16 11:17:32
 */
public class Headline extends BaseModel<Headline> {

	private static final long serialVersionUID = 1L;

	public static final Headline dao = new Headline();

	@Override
	public String tableName() {
		return "a2017_djl_headline";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:最新头条
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月28 9:42:33
	 * @修改:
	 */
	public Record getNewest() {
		StringBuffer sql = new StringBuffer();
		sql.append("select title,id from ");
		sql.append(this.tableName());
		sql.append(" where ");
		sql.append(" isDel='0' ");
		sql.append(" order by addTime desc ");
		return Db.findFirst(sql.toString());
	}

}
