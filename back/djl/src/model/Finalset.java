package model;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 常量 model
 *
 * @version 1.0
 * @since 2017-6-14 17:03:21
 */
public class Finalset extends BaseModel<Finalset> {

	private static final long serialVersionUID = 1L;

	public static final Finalset dao = new Finalset();

	@Override
	public String tableName() {
		return "a2017_djl_finalset";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:根据id获取value
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月23 2:55:54
	 * @修改:
	 */
	public String getValueById(String id) {

		Record result = this.showModel(id, new JSONObject());
		if (result == null) {
			return "";
		}
		return result.get("value") == null ? "" : String.valueOf(result
				.get("value"));

	}

}
