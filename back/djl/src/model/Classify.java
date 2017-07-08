package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 广告类目 model
 *
 * @version 1.0
 * @since 2017-6-16 16:01:32
 */
public class Classify extends BaseModel<Classify> {

	private static final long serialVersionUID = 1L;

	public static final Classify dao = new Classify();

	@Override
	public String tableName() {
		return "a2017_djl_classify";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:获取所有分类
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月19 8:10:39
	 * @修改:
	 */
	public List<Record> getList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,title,icon from ");
		sql.append(this.tableName());
		sql.append(" where ");
		sql.append(" (isDel='0' or isDel='1') ");
		sql.append(" order by isDel desc");
		return Db.find(sql.toString());
	}
}
