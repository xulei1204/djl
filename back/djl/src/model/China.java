package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 全国省市区街道 model
 *
 * @version 1.0
 * @since 2017-6-19 17:55:37
 */
public class China extends BaseModel<China> {

	private static final long serialVersionUID = 1L;

	public static final China dao = new China();

	@Override
	public String tableName() {
		return "a2017_djl_china";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:省列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月19 7:39:31
	 * @修改:
	 */
	public List<Record> getProList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select code,name from ");
		sql.append(this.tableName());
		sql.append(" where ");
		sql.append(" level=1");
		return Db.find(sql.toString());
	}

	/**
	 * @方法名:根据省获取城市
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月20 9:46:42
	 * @修改:
	 */
	public List<Record> getCityList(String proId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select code,name from ");
		sql.append(this.tableName());
		sql.append(" where ");
		sql.append(" level=2 ");
		sql.append(" and parentId=?");
		return Db.find(sql.toString(), proId);
	}

	/**
	 * @方法名:根据code获取name
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月21 3:21:47
	 * @修改:
	 */
	public String findName(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append("select name from ");
		sql.append(this.tableName());
		sql.append(" where ");
		sql.append("code=?");
		Record result = Db.findFirst(sql.toString(), code);
		return result != null ? result.getStr("name") : "";

	}
}
