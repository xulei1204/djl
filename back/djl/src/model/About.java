package model;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * model
 *
 * @version 1.0
 * @since 2017-6-13 13:38:33
 */
public class About extends BaseModel<About> {

	private static final long serialVersionUID = 1L;

	public static final About dao = new About();

	@Override
	public String tableName() {
		return "a2017_djl_about";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月14 10:56:45
	 * @修改:
	 */
	public Page<Record> getList(int page, int pageSize, JSONObject where) {

		return this.getPaging(page, pageSize, where);

	}

}
